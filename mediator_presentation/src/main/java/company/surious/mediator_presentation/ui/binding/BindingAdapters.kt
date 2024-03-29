package company.surious.mediator_presentation.ui.binding

import androidx.databinding.BindingAdapter
import com.google.android.gms.common.SignInButton

@BindingAdapter("android:onClick")
fun bindSignInOnClick(button: SignInButton, method: () -> Unit) {
    button.setOnClickListener { method.invoke() }
}