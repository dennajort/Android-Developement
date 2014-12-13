package fr.dennajort.fibonaccicommon;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jean-Baptiste on 13/12/2014.
 */

public class FibonacciResponse implements Parcelable {
    private final long result;
    private final long duration;

    public FibonacciResponse(long result, long duration) {
        this.result = result;
        this.duration = duration;
    }

    public long getResult() {
        return result;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(result);
        dest.writeLong(duration);
    }

    public static final Parcelable.Creator<FibonacciResponse> CREATOR = new Parcelable.Creator<FibonacciResponse>() {
        public FibonacciResponse createFromParcel(Parcel in) {
            return new FibonacciResponse(in.readLong(), in.readLong());
        }
        public FibonacciResponse[] newArray(int size) {
            return new FibonacciResponse[size];
        }
    };
}
