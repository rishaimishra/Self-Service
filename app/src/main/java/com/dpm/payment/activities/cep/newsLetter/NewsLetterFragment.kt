package com.dpm.payment.activities.cep.newsLetter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dpm.payment.activities.cep.ActivityCep
import com.dpm.payment.activities.cep.MyProfileFragment
import com.dpm.payment.activities.cep.NotificationFragment
import com.dpm.payment.activities.cep.information_tips.InformationTipsAdapter
import com.dpm.payment.activities.cep.newsLetter.model.NewsDataItem
import com.dpm.payment.activities.cep.newsLetter.model.NewsLetterResponse
import com.dpm.payment.adapters.NewsLetterAdapter
import com.dpm.payment.retrofit.Utills.ApiRequest
import com.dpm.payment.retrofit.Utills.ToastUtils
import com.dpm.payment.retrofit.interfaces.OnCallBackListner
import com.dpm.payment.utils.RestApiUrl.GET_NEWS_LETTER
import com.dpm.payment.utils.RestApiUrl.GET_TIP
import com.google.gson.Gson
import com.payment.R
import com.payment.databinding.FragmentNewsLetterBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class NewsLetterFragment : Fragment(), OnCallBackListner {

    private var _binding: FragmentNewsLetterBinding? = null
    private val binding get() = _binding!!

    val apiRequest by lazy { ApiRequest(requireContext(), this) }
    val adapter by lazy { com.dpm.payment.activities.cep.newsLetter.NewsLetterAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsLetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
        binding.rvNewsLetter.adapter = adapter

        adapter.setOnItemClickListener { story ->
            (requireActivity() as ActivityCep).startFragment(NewsDetailsFragment.newInstance(story.toString()))

        }

        /*calling the api*/
        apiRequest.callGetRequest(GET_NEWS_LETTER, GET_NEWS_LETTER)
    }

    @SuppressLint("SetTextI18n")
    private fun initToolbar(view: View) {
       // view.findViewById<ImageView>(R.id.ivProfile).isVisible=false
        val tvTitle = view.findViewById<AppCompatTextView>(R.id.toolbar_tv_header)
        val ivHome = view.findViewById<ImageView>(R.id.toolbar_iv_home)
        tvTitle.text = "Newsletter"
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

    fun setHighlightedNews(dataItem: NewsDataItem) {
        binding.apply {
            newsHeadLine.text = dataItem.headline
            Picasso.get().load(dataItem.headlineImg()).placeholder(R.drawable.ic_video_image)
                .error(R.drawable.ic_video_image).into(ivVideo)
            tvDate.text = dataItem.getCreatedDate()
            tvTimeAgo.text = dataItem.timeAgo()


        }
    }

    override fun OnCallBackSuccess(tag: String?, response: String) {

        if (tag == GET_NEWS_LETTER) {
            val res = Gson().fromJson(response, NewsLetterResponse::class.java)
            if (res.status.equals("success")) {

                if (res.data!!.isNotEmpty()) {
                    setHighlightedNews(res.data[0]!!)
                }


                adapter.submitList(res.data)
            } else ToastUtils.showShort(requireActivity(), res.message)


        }

    }

    override fun OnCallBackError(tag: String?, error: String?, i: Int) {
        ToastUtils.showShort(requireActivity(), error)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

