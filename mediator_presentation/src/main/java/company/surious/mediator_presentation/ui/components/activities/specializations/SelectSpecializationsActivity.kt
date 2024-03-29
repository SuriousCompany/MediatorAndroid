package company.surious.mediator_presentation.ui.components.activities.specializations

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import company.surious.mediator_presentation.R
import company.surious.mediator_presentation.databinding.ActivitySelectSpecializationsBinding
import company.surious.mediator_presentation.ui.base.ViewModelFactory
import company.surious.mediator_presentation.ui.utils.extensions.DialogUtils
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_select_specializations.*
import javax.inject.Inject


class SelectSpecializationsActivity : DaggerAppCompatActivity() {
    companion object {
        const val SELECTED_SPECIALIZATIONS_KEY = "selectedSpecializations"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val selectedSpecializations = ArrayList<SelectableSpecialization>()
    private lateinit var specializationsAdapter: SelectableSpecializationsAdapter
    private lateinit var dialogUtils: DialogUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySelectSpecializationsBinding>(
            this,
            R.layout.activity_select_specializations
        )
        initAdapter()
        initDataBinding(binding)
        initViewModel()
        dialogUtils = DialogUtils(supportFragmentManager)
        dialogUtils.showLoadingDialog()
        initSearchView()
    }

    private fun initSearchView() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        with(specializationsSearchView) {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isSubmitButtonEnabled = false
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    specializationsAdapter.filter.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    specializationsAdapter.filter.filter(newText)
                    return false
                }

            })
        }
    }

    private fun initViewModel() {
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory)[SpecializationsListViewModel::class.java]
        viewModel.updateSpecializationsFunction = ::updateSpecializations
    }

    private fun initDataBinding(binding: ActivitySelectSpecializationsBinding) {
        binding.lifecycleOwner = this
        binding.specializationsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.specializationsRecyclerView.adapter = specializationsAdapter
        binding.eventsHandler = SelectSpecializationsActivityEventsHandler()
    }

    private fun initAdapter() {
        specializationsAdapter = SelectableSpecializationsAdapter()
        specializationsAdapter.onSelectedListener = ::onSpecializationSelected
        specializationsAdapter.onUnSelectedListener = ::onSpecializationUnSelected
    }

    private fun onSpecializationSelected(specialization: SelectableSpecialization) {
        selectedSpecializations.add(specialization)
        addChip(specialization)
    }

    private fun addChip(specialization: SelectableSpecialization) {
        val chipView = LayoutInflater.from(this)
            .inflate(
                R.layout.chip_specialization_selectable,
                selectedSpecializationsChipGroup,
                false
            ) as Chip
        with(chipView) {
            text = specialization.getFormattedName()
            tag = specialization.id.toString()
            setOnClickListener {
                onSpecializationUnSelected(specialization)
            }
            setOnCloseIconClickListener {
                onSpecializationUnSelected(specialization)
            }
        }

        selectedSpecializationsChipGroup.addView(chipView)
    }

    private fun onSpecializationUnSelected(specialization: SelectableSpecialization) {
        selectedSpecializations.remove(specialization)
        selectedSpecializationsChipGroup.removeView(
            selectedSpecializationsChipGroup.findViewWithTag<View>(
                specialization.id.toString()
            )
        )
        specializationsAdapter.unSelectSpecialization(specialization.id)
    }

    private fun updateSpecializations(specializations: List<SelectableSpecialization>) {
        dialogUtils.hideLoadingDialog()
        selectedSpecializations.clear()
        specializationsAdapter.setAll(specializations.sortedWith(Comparator { o1, o2 ->
            run {
                val o1NameStart: String = (o1.parentSpecialization?.nameRu ?: "") + o1.nameRu
                val o2NameStart: String = (o2.parentSpecialization?.nameRu ?: "") + o2.nameRu
                o1NameStart.compareTo(o2NameStart, true)
            }
        }))
    }

    inner class SelectSpecializationsActivityEventsHandler {
        fun onSelectedFabClicked() {
            setResult(
                Activity.RESULT_OK,
                Intent().apply {
                    putParcelableArrayListExtra(
                        SELECTED_SPECIALIZATIONS_KEY,
                        selectedSpecializations
                    )
                })
            finish()
        }

        fun onSearchViewClicked() {
            specializationsSearchView.isIconified = false
        }
    }
}
