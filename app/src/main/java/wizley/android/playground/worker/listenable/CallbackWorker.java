package wizley.android.playground.worker.listenable;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

import com.google.common.util.concurrent.ListenableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// implementation "androidx.concurrent:concurrent-futures:1.0.0"
public class CallbackWorker extends ListenableWorker {
    /**
     * @param appContext   The application {@link Context}
     * @param workerParams Parameters to setup the internal state of this worker
     */
    public CallbackWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public ListenableFuture<Result> startWork() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver<Result>() {
            @Nullable
            @Override
            public Object attachCompleter(@NonNull final CallbackToFutureAdapter.Completer<Result> completer) throws Exception {
                Callback callback = new Callback() {
                    int success = 0;

                    @Override
                    public void onResponse(Call call, Response response) {
                        ++success;
                        if (success == 100) {
                            completer.set(Result.success());
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        completer.setException(t);
                    }
                };

                // If task be cancelled, you can call CancellationListener
                // completer.addCancellationListener(cancelDownloadsRunnable, this);

                for (int i = 0; i < 100; i++) {
                    // call server and get response with callback
                    // getInfo("https://bughunting.kr", callback);
                }

                return callback;
            }
        });
    }
}
