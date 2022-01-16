package com.guzzardi.profileslist.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.guzzardi.profileslist.R
import com.guzzardi.profileslist.databinding.FragmentCreateProfileBinding
import com.guzzardi.profileslist.view.utils.setActionBarTitle
import com.guzzardi.profileslist.viewmodel.UserProfilesViewModel

class CreateProfileFragment : Fragment() {

    private val userProfilesViewModel: UserProfilesViewModel by activityViewModels()

    private var binding: FragmentCreateProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle(getString(R.string.fragment_create_profile_title))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun
}
