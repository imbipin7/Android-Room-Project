

import com.example.androidroomproject.validations.Validator
import com.example.androidroomproject.validations.ValidatorUtils


class PhoneValidator(private val editable: String, val message: String) :
    Validator {
    override fun isValid(): Boolean {
        return ValidatorUtils.isMobileValid(editable.trim())
    }

    override fun message(): String {
        return message
    }
}