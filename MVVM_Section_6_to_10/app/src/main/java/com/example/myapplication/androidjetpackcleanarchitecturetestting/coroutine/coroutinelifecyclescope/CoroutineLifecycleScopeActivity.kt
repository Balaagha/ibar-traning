package com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.coroutinelifecyclescope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.coroutinelifecyclescope.ui.main.CoroutineLifecycleScopeFragment
import kotlinx.android.synthetic.main.coroutine_lifecycle_scope_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineLifecycleScopeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutine_lifecycle_scope_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CoroutineLifecycleScopeFragment.newInstance())
                    .commitNow()
        }
        lifecycleScope.launch(Dispatchers.Main) {
            delay(5000)
            progressBar.visibility = View.VISIBLE
            delay(7000)
            progressBar.visibility = View.GONE
        }
        lifecycleScope.launchWhenCreated {
            //if you have some long running operations which should happen only once during the lifecycle of the activity or fragment you would use this coroutine. So this coroutine will launch, when the activity or fragment created for the first time
        }
        lifecycleScope.launchWhenStarted {
            // So in this case, coroutine will launch when the activity or fragment started. we need to run a FragmentTransaction inside our coroutine scope, for that we need our Fragment lifecycle to be at least started. We can write that code inside this block.
        }
        lifecycleScope.launchWhenResumed {
            // If we have a requirement to start a long running task just after the app is up and running we just need it.
        }
    }
}