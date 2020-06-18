package wizley.android.playground.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import wizley.android.playground.R

class KTDataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityDatabinding2Binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding2)

        val model: KTMyViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(KTMyViewModel::class.java)

        binding.customViewModel = model
        binding.lifecycleOwner = this

    }
}