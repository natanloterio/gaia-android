package ai.gaiahub.tracking.events.login

internal class LoginEventBuilderImpl() : LoginEventBuilder() {

    private var email: String = ""
    private var success: Boolean = false
    private var error: String? = null

    override fun email(email: String) = apply { this.email = email }
    override fun success(success: Boolean) = apply { this.success = success }
    override fun error(error: String?) = apply { this.error = error }

    override suspend fun build(): LoginEvent {
        val loginEvent = LoginEvent(
            email = email,
            isSuccess = success,
            error = error
        )

        return loginEvent
    }
}