package com.hefny.hady.gadphasetwoproject.ui.submit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.hefny.hady.gadphasetwoproject.R
import com.hefny.hady.gadphasetwoproject.utils.Resource
import kotlinx.android.synthetic.main.activity_submit.*

class SubmitActivity : AppCompatActivity() {
    private lateinit var submitViewModel: SubmitViewModel
    private lateinit var firstNameLayout: TextInputLayout
    private lateinit var firstNameEditText: TextInputEditText
    private lateinit var lastNameLayout: TextInputLayout
    private lateinit var lastNameEditText: TextInputEditText
    private lateinit var emailLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var githubLayout: TextInputLayout
    private lateinit var githubEditText: TextInputEditText

    companion object {
        private const val FIRST_NAME_KEY = "com.hefny.hady.gadphasetwoproject.FirstName"
        private const val LAST_NAME_KEY = "com.hefny.hady.gadphasetwoproject.LastName"
        private const val EMAIL_KEY = "com.hefny.hady.gadphasetwoproject.EMAIL"
        private const val GITHUB_KEY = "com.hefny.hady.gadphasetwoproject.GITHUB"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        firstNameLayout = findViewById(R.id.first_name_text_layout)
        firstNameEditText = findViewById(R.id.first_name_edit_text)
        lastNameLayout = findViewById(R.id.last_name_text_layout)
        lastNameEditText = findViewById(R.id.last_name_edit_text)
        emailLayout = findViewById(R.id.email_text_layout)
        emailEditText = findViewById(R.id.email_edit_text)
        githubLayout = findViewById(R.id.github_text_layout)
        githubEditText = findViewById(R.id.github_edit_text)
        submitViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                SubmitViewModel::class.java
            )
        // restore previously saved state of edit text fields
        restoreState(savedInstanceState)
        back_btn.setOnClickListener {
            onBackPressed()
        }
        activity_submit_btn.setOnClickListener {
            submitProject(
                firstNameEditText.text.toString(),
                lastNameEditText.text.toString(),
                emailEditText.text.toString(),
                githubEditText.text.toString()
            )
        }
        subscribeObserver()
    }

    private fun subscribeObserver() {
        submitViewModel.submitProjectLiveData.observe(this, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    submit_activity_progressbar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    submit_activity_progressbar.visibility = View.GONE
                }
                is Resource.Error -> {
                    submit_activity_progressbar.visibility = View.GONE
                }
            }
        })
    }

    private fun submitProject(firstName: String, lastName: String, email: String, github: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView: View = layoutInflater.inflate(R.layout.success_dialog_layout,null)
        dialogBuilder.setView(dialogView)
        val dialog = dialogBuilder.create()
        dialog.window?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.rounded_background
            )
        )
        dialog.show()

//        when {
//            firstName.isBlank() -> {
//                firstNameLayout.error = getString(R.string.first_name_error)
//            }
//            lastName.isBlank() -> {
//                firstNameLayout.error = null
//                lastNameLayout.error = getString(R.string.last_name_error)
//            }
//            email.isBlank() -> {
//                firstNameLayout.error = null
//                lastNameLayout.error = null
//                emailLayout.error = getString(R.string.email_error)
//            }
//            github.isBlank() -> {
//                firstNameLayout.error = null
//                lastNameLayout.error = null
//                emailLayout.error = null
//                githubLayout.error = getString(R.string.github_error)
//            }
//            else -> {
//                firstNameLayout.error = null
//                lastNameLayout.error = null
//                emailLayout.error = null
//                githubLayout.error = null
//                submitViewModel.submitProject(
//                    firstName, lastName, email, github
//                )
//            }
//        }
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
            firstName?.let { firstNameEditText.setText(it) }
            lastName?.let { lastNameEditText.setText(it) }
            email?.let { emailEditText.setText(it) }
            github?.let { githubEditText.setText(it) }
        }
    }

    // save edit text fields on configuration changes
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(FIRST_NAME_KEY, firstNameEditText.text.toString())
        outState.putString(LAST_NAME_KEY, lastNameEditText.text.toString())
        outState.putString(EMAIL_KEY, emailEditText.text.toString())
        outState.putString(GITHUB_KEY, githubEditText.text.toString())
    }
}