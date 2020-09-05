package com.hefny.hady.gadphasetwoproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hefny.hady.gadphasetwoproject.R
import com.hefny.hady.gadphasetwoproject.api.responses.Leader
import com.hefny.hady.gadphasetwoproject.utils.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class LeadersFragment : Fragment() {
    private lateinit var leadersViewModel: LeadersViewModel
    private var learningLeaders = ArrayList<Leader>()
    private var skillIqLeaders = ArrayList<Leader>()
    private lateinit var learnersAdapter: LearnersAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        leadersViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(LeadersViewModel::class.java)
        var counter = 1
        arguments?.let {
            counter = it.getInt(ARG_SECTION_NUMBER)
        }
        when (counter) {
            1 -> {
                observeLearningLeaders()
            }
            2 -> {
                observeSkillIqLeaders()
            }
        }
    }

    private fun initRecyclerView() {
        leaders_recycler_view.run {
            layoutManager = LinearLayoutManager(requireContext())
            learnersAdapter = LearnersAdapter()
            adapter = learnersAdapter
        }
    }

    private fun observeLearningLeaders() {
        leadersViewModel.learningLeadersLiveData.observe(
            viewLifecycleOwner,
            Observer { dataResource ->
                when (dataResource) {
                    is Resource.Loading -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        progress_bar.visibility = View.GONE
                        dataResource.data?.let {
                            learningLeaders.clear()
                            learningLeaders.addAll(it)
                            learnersAdapter.setLeadersList(learningLeaders)
                        }

                    }
                    is Resource.Error -> {
                        progress_bar.visibility = View.GONE
                    }
                }
            })
    }

    private fun observeSkillIqLeaders() {
        leadersViewModel.skillIqLeadersLiveData.observe(
            viewLifecycleOwner,
            Observer { dataResource ->
                when (dataResource) {
                    is Resource.Loading -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        progress_bar.visibility = View.GONE
                        dataResource.data?.let {
                            skillIqLeaders.clear()
                            skillIqLeaders.addAll(it)
                            learnersAdapter.setLeadersList(skillIqLeaders)
                        }
                    }
                    is Resource.Error -> {
                        progress_bar.visibility = View.GONE
                    }
                }
            })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): LeadersFragment {
            return LeadersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}