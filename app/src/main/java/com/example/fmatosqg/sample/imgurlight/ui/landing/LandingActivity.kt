package com.example.fmatosqg.sample.imgurlight.ui.landing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.fmatosqg.sample.imgurlight.R
import com.example.fmatosqg.sample.imgurlight.common.ImgurApplication

import com.jakewharton.rxbinding2.support.v7.widget.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.landing_activity.*
import org.jetbrains.anko.design.snackbar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LandingActivity : AppCompatActivity() {

    private lateinit var postAdapter: PostListAdapter

    private var lastKeyword: String = "" // TODO good candidate for arch components view model
    private var isSwitchActive = false

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var
            landingActivityPresenter: LandingActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_activity)


        ImgurApplication.graph.inject(this)
        setupUi()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.dispose()
    }

    ////////////////////////////////////////////
    // end of overriden methods


    private fun setupUi() {

        landing_list.layoutManager = LinearLayoutManager(this)

        postAdapter = PostListAdapter()
        landing_list.adapter = postAdapter

        landing_swipe.setOnRefreshListener {
            search(lastKeyword, isSwitchActive)
        }


        RxSearchView
                .queryTextChanges(landing_search)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            lastKeyword = it.toString()
                            search(lastKeyword, isSwitchActive)
                        }
                )

        landing_switch.setOnCheckedChangeListener { button, isChecked ->

            isSwitchActive = isChecked
            val message = if (isSwitchActive) {
                R.string.landing_switch_snackbar_on
            } else {
                R.string.landing_switch_snackbar_off
            }
            snackbar(landing_switch, message)

            search(lastKeyword, isSwitchActive)
        }

        search(lastKeyword, isSwitchActive)
    }

    private fun search(keyword: String? = null, isSwitchActive: Boolean) {


        landing_swipe.isRefreshing = true
        compositeDisposable.clear()

        compositeDisposable.add(
                landingActivityPresenter
                        .getData(keyword, isSwitchActive)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onSuccess = {
                                    landing_list.scrollToPosition(0)
                                    postAdapter.setData(it)
                                    landing_swipe.isRefreshing = false
                                },
                                onError = {
                                    snackbar(landing_list, R.string.landing_server_data_error)
                                    landing_swipe.isRefreshing = false
                                }
                        )
        )
    }
}


