### It's not here
** Keeping a production keystore inside the source tree (e.g., `keystore/release.keystore`) and checking it into your repo is *not* safe.**

It exposes the key to anyone who can clone or pull the repository, which means:

| Risk | What could happen |
|------|-------------------|
| **Unauthorized signing** | A malicious actor could sign an app with your keystore, publish a fake update that looks like yours, and trick users into installing it. |
| **Key compromise** | If the key is ever leaked (e.g., via a public repo, CI logs, or a data breach), you lose control over future updates – you can’t revoke or replace the key without losing the ability to update existing installs. |
| **Compliance violations** | Some security standards (SOC 2, ISO 27001, etc.) require strict controls on cryptographic keys; storing them in VCS usually violates those controls. |

---

## How to keep a keystore *safe* while still using it for builds

Below are industry‑standard practices that balance **security** with the **automation** Fastlane (or any CI system) requires.

| Step | What you should do | Why |
|------|--------------------|-----|
| 1️⃣ **Generate the keystore outside of source control** | `keytool -genkeypair … -keystore release.keystore` in a secure location on your local machine or a dedicated key‑management server. |


## Keeping an Android keystore safe **and** letting Fastlane use it

The goal is twofold:

1. **Never commit the keystore (or its passwords) into VCS.**
2. **Make sure your CI/CD pipeline can still access the key when building and signing.**

Below are the most common, production‑grade approaches.

---

### 1️⃣ Store the keystore in a secure secret store

| Platform | Typical tool | How it works |
|----------|--------------|---------------|
| GitHub Actions | **GitHub Secrets** | Keystore file is base64‑encoded and stored as a secret; Fastlane decodes it at runtime. |
| GitLab CI | **CI/CD variables** (protected) or the **File variable** feature | Same idea: upload the keystore file, then mount/echo it during the job. |
| Bitbucket Pipelines | **Repository Variables** | Store the base64‑encoded keystore and decode in the script. |
| CircleCI / Travis CI / GitLab Runner (self‑hosted) | **Environment variables** or **encrypted files** (`git-crypt`, `sops`) | |

> **Tip:** Use *protected* / *masked* secrets so that they’re not displayed in build logs.

---

### 2️⃣ Keep the keystore file out of the repo

Add it to `.gitignore`:

```git
# Android keystore
keystore/release.keystore
```

If you already committed it, run `git filter-branch` or use BFG Repo‑Cleaner to purge it from history.

---

### 3️⃣ Provide Fastlane with the keystore at build time

#### a. Base64‑encoded file (GitHub Actions example)

```yaml
# .github/workflows/android.yml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      # Decode keystore from secret into the expected path
      - name: Restore keystore
        run: |
          echo "${{ secrets.RELEASE_KEYSTORE }}" | base64 --decode > ${{ github.workspace }}/keystore/release.keystore

      - name: Install Fastlane
        uses: ruby/setup-ruby@v1
        with:
          ruby-version: 3.2

      - name: Build & publish
        run: |
          bundle install
          fastlane android beta
```

`secrets.RELEASE_KEYSTORE` is the **base64** string of your keystore file.

#### b. Direct upload (GitLab CI example)

```yaml
# .gitlab-ci.yml
stages:
  - build

android_build:
  stage: build
  image: ruby:3.2
  variables:
    KEYS_PATH: $CI_PROJECT_DIR/keystore/release.keystore
  script:
    - echo "$RELEASE_KEYSTORE" | base64 -d > $KEYS_PATH
    - gem install bundler fastlane
    - bundle exec fastlane android beta
  only:
    - main
```

`$RELEASE_KEYSTORE` is a protected CI variable that holds the base64 string.

---

### 4️⃣ Use Fastlane to sign with the keystore

In your `Fastfile`:

```ruby
platform :android do
  desc "Build & distribute"
  lane :beta do
    gradle(
      task: "assemble",
      build_type: "Release",
      signing_options: {
        keystore_path:     "./keystore/release.keystore",
        keystore_password: ENV["KEYSTORE_PASSWORD"],
        key_alias:         "myapp",
        key_password:     ENV["KEY_PASSWORD"]
      }
    )

    firebase_app_distribution(
      # … your Firebase config …
    )
  end
end
```

* **`keystore_path`** – points to the decoded file in the working directory.
* **Passwords** are read from environment variables (`KEYSTORE_PASSWORD`, `KEY_PASSWORD`). Store those as masked secrets in your CI.

---

### 5️⃣ Optional: Encrypt the keystore on disk (local dev)

If you’re running Fastlane locally and want an extra layer of protection:

1. Store the keystore encrypted with GPG or a password‑protected ZIP.
2. In `Fastfile`, decrypt it just before signing:

```ruby
sh "gpg --batch --yes -d -o ./keystore/release.keystore ./keystore/encrypted-release-keystore.gpg"
```

(Only do this if you have a secure key on your local machine.)

---

## Summary Checklist

| ✅ | Item |
|----|------|
| 1️⃣ | `keystore/` (or any keystore folder) is **ignored** in `.gitignore`. |
| 2️⃣ | The actual file is **never committed**; purge history if needed. |
| 3️⃣ | Keystore + passwords are stored as **CI secrets** (GitHub, GitLab, etc.). |
| 4️⃣ | Fastlane receives the keystore at build time via a temporary file. |
| 5️⃣ | Passwords are passed through environment variables, not hard‑coded. |

With this setup you keep the key out of your repo while still letting Fastlane automate signing and distribution safely.