package uz.muhsinov_dev.mohirdevworkmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random


const val TAG = "TAG"
class MyWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        val random = Random.nextInt(100)

//
//        val formatter = SimpleDateFormat("mm:ss", Locale.ENGLISH)
//        Log.d(TAG, "doWork: ${formatter.format(System.currentTimeMillis())} attemted $runAttemptCount")
//        if (runAttemptCount<3)
//        return Result.retry()
        repeat(100){
            setProgress(workDataOf("progress" to it+1))
        }
      return  if (random%2==0){
            Result.success(workDataOf("output" to "photo downloaded successfully"))
        }else{

            Result.failure(workDataOf("output" to "photo  downloaded failure"))
      }

    }

}