package com.hefny.hady.gadphasetwoproject.ui.submit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hefny.hady.gadphasetwoproject.R
import kotlinx.android.synthetic.main.activity_submit.*

class SubmitActivity : AppCompatActivity() {
    companion object {
        private const val FIRST_NAME_KEY = "com.hefny.hady.gadphasetwoproject.FirstName"
        private const val LAST_NAME_KEY = "com.hefny.hady.gadphasetwoproject.LastName"
        private const val EMAIL_KEY = "com.hefny.hady.gadphasetwoproject.EMAIL"
        private const val GITHUB_KEY = "com.hefny.hady.gadphasetwoproject.GITHUB"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        // restore previously saved state of edit text fields
        restoreState(savedInstanceState)
        back_btn.setOnClickListener {
            onBackPressed()
        }
    }

    // restore previously saved state of edit text fields
    private fun restoreState(savedInstanceState: Bundle?) {
        var firstName: String?
        var lastName: String?
        var email: String?
        var github: String?
        savedInstanceState?.let {
            firstName = it.getString(FIRST_NAME_KEY)
            lastName = it.getString(LAST_NAME_KEY)
            email = it.getString(EMAIL_KEY)
            github = it.getString(GITHUB_KEY)
            // set restored text to edit text fields
            firstName?.let { first_name_edit_text.setText(it) }
            lastName?.let { last_name_edit_text.setText(it) }
            email?.let { email_edit_text.setText(it) }
            github?.let { github_edit_text.setText(it) }
        }
    }

    // save edit text fields on configuration changes
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(FIRST_NAME_KEY, first_name_edit_text.text.toString())
        outState.putString(LAST_NAME_KEY, last_name_edit_text.text.toString())
        outState.putString(EMAIL_KEY, email_edit_text.text.toString())
        outState.putString(GITHUB_KEY, github_edit_text.text.toString())
    }
}