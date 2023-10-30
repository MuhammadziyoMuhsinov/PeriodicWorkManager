package uz.muhsinov_dev.mohirdevworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import uz.muhsinov_dev.mohirdevworkmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //constraints
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)

            .build()

        val periodic = PeriodicWorkRequestBuilder<MyWorker>(20,TimeUnit.SECONDS).build()


        binding.oneTimeWorkBtn.setOnClickListener {
            WorkManager.getInstance(this).enqueueUniquePeriodicWork("periodic",ExistingPeriodicWorkPolicy.REPLACE,periodic)
            WorkManager.getInstance(this).getWorkInfoByIdLiveData(periodic.id).observeForever {
            val progress = it.progress
                val number = progress.getInt("progress",0)
                Log.d(TAG, "onCreate: $number")
            }





        }
        //request onetimeWork
        val request = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints)
            .setBackoffCriteria(BackoffPolicy.LINEAR,WorkRequest.MIN_BACKOFF_MILLIS,TimeUnit.MILLISECONDS)
            .setInputData(workDataOf("image" to "imageUrl"))
            .build()



//        binding.oneTimeWorkBtn.setOnClickListener {
//            WorkManager.getInstance(this)
//                .enqueueUniqueWork("my_worker",ExistingWorkPolicy.REPLACE,request)
//
//            WorkManager.getInstance(this)
//                .getWorkInfoByIdLiveData(request.id)
//                .observeForever {workInfo->
//
//                    Log.d("TAG", "onCreate: ${workInfo.progress.getInt("progress",0)}")
//                    val result =  workInfo.outputData.getString("output")
//                    if (workInfo.state == WorkInfo.State.SUCCEEDED){
//
//                        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
//                    }else if (workInfo.state == WorkInfo.State.FAILED){
//                        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//        }




    }
}