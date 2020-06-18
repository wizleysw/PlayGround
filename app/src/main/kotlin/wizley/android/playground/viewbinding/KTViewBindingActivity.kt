package wizley.android.playground.viewbinding

import android.app.Activity
import android.os.Bundle
import wizley.android.playground.databinding.ActivityViewbindingBinding

class KTViewBindingActivity : Activity(){
    private lateinit var binding : ActivityViewbindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewbindingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textView.text = "TextView"
        binding.button.text = "Button"
    }
}