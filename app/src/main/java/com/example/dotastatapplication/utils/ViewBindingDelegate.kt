package com.example.dotastatapplication.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> Fragment.viewBinding(): ViewBindingDelegate<T> {
    return ViewBindingDelegate(this, T::class)
}

class ViewBindingDelegate<T : ViewBinding>(
    private val fragment: Fragment,
    private val viewBindingClass: KClass<T>
) : ReadOnlyProperty<Any?, T> {

    private var binding: T? = null

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { lifecycleOwner ->
            lifecycleOwner.lifecycle.addObserver(
                LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_DESTROY) binding = null
                }
            )
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T =
        binding ?: obtainBinding()

    private fun obtainBinding(): T {
        val view = checkNotNull(fragment.view) {
            "ViewBinding is only valid between onCreateView and onDestroyView."
        }
        return viewBindingClass.bind(view)
            .also { binding = it }
    }
}

fun <T : ViewBinding> KClass<T>.bind(rootView: View): T {
    val inflateMethod = java.getBindMethod()
    @Suppress("UNCHECKED_CAST")
    return inflateMethod.invoke(null, rootView) as T
}

private val bindMethodsCache = mutableMapOf<Class<out ViewBinding>, Method>()

private fun Class<out ViewBinding>.getBindMethod(): Method {
    return bindMethodsCache.getOrPut(this) { getDeclaredMethod("bind", View::class.java) }
}
