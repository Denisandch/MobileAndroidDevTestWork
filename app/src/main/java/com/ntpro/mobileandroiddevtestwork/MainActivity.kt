package com.ntpro.mobileandroiddevtestwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import com.ntpro.mobileandroiddevtestwork.adapter.DealAdapter
import com.ntpro.mobileandroiddevtestwork.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var dealAdapter: DealAdapter
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private val viewModel by viewModel<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        dealAdapter = DealAdapter(this)
        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAdapter.addAll(Column.values().toList().map { it.columnNameUI })

        mainBinding.dealRecycler.adapter = dealAdapter
        mainBinding.spinnerFilterList.adapter = spinnerAdapter

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.deals.collectLatest { pagingData ->
                dealAdapter.submitData(pagingData)
            }
        }

        mainBinding.buttonRefresh.setOnClickListener {
            val filter = mainBinding.spinnerFilterList.selectedItem.toString()
            val isAsc = mainBinding.checkboxIsAsk.isChecked

            viewModel.setFilter(Column.fromColumnName(filter)!!, isAsc)

            Snackbar
                .make(
                    mainBinding.root,
                    resources.getString(R.string.data_updated),
                    Snackbar.LENGTH_SHORT
                )
                .show()

            mainBinding.dealRecycler.scrollToPosition(0)
        }

        viewModel.countOfNewDeals.observe(this) {
            mainBinding.textNewDeals.visibility = if (it != 0) View.VISIBLE else View.INVISIBLE
            mainBinding.textNewDeals.text = resources.getString(R.string.new_deals, it)
        }
    }
}