package androidx.lifecycle

open class SafeLiveData<T> @JvmOverloads constructor(private var data: T? = null) :
    MutableLiveData<T>(data) {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val pictorialObserver = PictorialObserver(observer, this.version > START_VERSION)
        super.observe(owner, pictorialObserver)
    }
}

class PictorialObserver<T>(
    private val realObserver: Observer<in T>,
    private var preventDispatch: Boolean = false
) : Observer<T> {
    override fun onChanged(value: T) {
        if (preventDispatch) {
            preventDispatch = false
            return
        }
        realObserver.onChanged(value)
    }

}