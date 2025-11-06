package com.desager.catslist.ui.screens.favourite_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.desager.catslist.R
import com.desager.catslist.databinding.FragmentFavouriteListBinding

class FavouriteListFragment : Fragment() {

    private var _binding: FragmentFavouriteListBinding? = null
    private val binding get() = _binding!!

    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonNavigate.setOnClickListener {
            navController.navigate(R.id.action_favourite_to_details)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}