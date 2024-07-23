package com.dpm.payment.activities.cep.garbageCollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dpm.payment.activities.cep.ActivityCep
import com.dpm.payment.activities.cep.MyProfileFragment
import com.dpm.payment.activities.cep.NotificationFragment
import com.dpm.payment.utils.pickTime
import com.payment.R
import com.payment.databinding.FragmentGarbageCollectionBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class GarbageCollectionFragment : Fragment() {


    private var _binding: FragmentGarbageCollectionBinding? = null
    private val binding get() = _binding!!

    private lateinit var calendarAdapter: CalendarAdapter
    private val days = mutableListOf<CalendarDay>()

    private val calendar = Calendar.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGarbageCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initToolbar(view)
    }

    private fun initView(view: View) {

       binding.apply {
        /*   calendarView.minDate = cal.timeInMillis
           calendarView.maxDate = cal.timeInMillis*/
           calendarRecyclerView.layoutManager = GridLayoutManager(requireContext(), 7)
           calendarAdapter = CalendarAdapter(days)
           calendarRecyclerView.adapter = calendarAdapter
           updateCalendar()

           nextMonthButton.setOnClickListener {
               calendar.add(Calendar.MONTH, 1)
               updateCalendar()
           }
           previousMonthButton.setOnClickListener {
               calendar.add(Calendar.MONTH, -1)
               updateCalendar()
           }
           txtPickTime.setOnClickListener { requireActivity().pickTime { hourOfDay, minute ->
               val formattedTime = String.format("%02d:%02d", hourOfDay, minute)
               txtSelectedTime.text = "Selected Time: $formattedTime"
           } }

       }

    }

    private fun updateCalendar() {
        days.clear()
        calendarAdapter.selectedDay=-1
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        binding.previousMonthButton.isVisible= month> Calendar.getInstance().get(Calendar.MONTH) || year >Calendar.getInstance().get(Calendar.YEAR)
        binding.monthYearText.text = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(calendar.time)

        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        for (i in 0 until firstDayOfWeek) {
            days.add(CalendarDay(0, false,calendar)) // Empty days
        }

        for (i in 1..daysInMonth) {
            val isAvailable = (i % 5 == 0) // Example logic for available days
            days.add(CalendarDay(i, isAvailable,calendar))
        }

        calendarAdapter.notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    private fun initToolbar(view: View) {
        val tvTitle = view.findViewById<AppCompatTextView>(R.id.toolbar_tv_header)
        val ivHome = view.findViewById<ImageView>(R.id.toolbar_iv_home)
        tvTitle.setText(R.string.garbage_collection)
        ivHome.setOnClickListener { v: View? ->
            parentFragmentManager.popBackStack()
        }
        val ivProfile = view.findViewById<AppCompatImageView>(R.id.ivProfile)
        val ivNotification = view.findViewById<AppCompatImageView>(R.id.ivNotification)
        ivProfile.setOnClickListener { v: View? ->
            (requireActivity() as ActivityCep).startFragment(MyProfileFragment.newInstance())
        }
        ivNotification.setOnClickListener { v: View? ->
            (requireActivity() as ActivityCep).startFragment(NotificationFragment.newInstance())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): GarbageCollectionFragment {
            return GarbageCollectionFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}