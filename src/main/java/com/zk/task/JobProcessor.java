package com.zk.task;

import com.zk.domain.JobInfo;
import com.zk.utils.StringSplit;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

@Component
public class JobProcessor implements PageProcessor {
    private String url = "https://search.51job.com/list/180200,000000,0000,32%252C01,9,99,%25E8%25BD%25AF%25E4%25BB%25B6%25E6%25B5%258B%25E8%25AF%2595,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    @Autowired
    private JobPipeline jobPipeline;

    @Override
    public void process(Page page) {
        List<Selectable> list = page.getHtml().css("div#resultList > div.el").nodes();
        //判断list中是否有元素,有说明是列表页,没有说明是详情页
        if (list != null && list.size() > 0) {
            //列表页
            for (Selectable selectable : list) {
                String listUrl = selectable.links().toString();
                page.addTargetRequest(listUrl);
            }
            String afterPage = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
            page.addTargetRequest(afterPage);

        } else {
            //详情页
            this.saveJobInfo(page);
        }


    }

    /**
     * 解析详情页面,将数据存入
     *
     * @param page
     */
    private void saveJobInfo(Page page) {
        JobInfo jobInfo = new JobInfo();
        Html html = page.getHtml();
        if (html != null) {
            try {
                jobInfo.setUrl(page.getUrl().toString());
                String jobAddr=html.css("div.bmsg > p.fp").toString().replace("上班地址：","").replace(" ","");
                if(jobAddr!=null){

                    jobInfo.setJobAddr(Jsoup.parse(jobAddr).text());
                }else {
                    jobInfo.setJobAddr("");
                }

                jobInfo.setCompanyName(html.css("p.cname > a", "text").nodes().get(0).toString());
                jobInfo.setCompanyAddr(Jsoup.parse(html.css("div.cn > p.ltype").toString()).text().split("\\|")[0].replace(" ", ""));
                jobInfo.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text().replace(" ",""));
                jobInfo.setJobName(Jsoup.parse(html.css("div.cn > h1").toString()).text().replace(" ",""));
                jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text().replace(" ",""));
                jobInfo.setCompanyName(Jsoup.parse(html.css("div.cn > p.cname a").nodes().get(0).toString()).text().replace(" ",""));
                String time = Jsoup.parse(html.css("div.cn > p.ltype").toString()).text().replace(" ","");
                jobInfo.setTime(time.substring(time.lastIndexOf("发布") - 5, time.lastIndexOf("发布")));
                Integer[] salary = StringSplit.getInt(Jsoup.parse(html.css("div.cn strong").toString()).text());
                jobInfo.setSalaryMin(salary[0]);
                jobInfo.setSalaryMax(salary[1]);
            } catch (Exception e) {
                System.out.println("元素未找到");
            }

            page.putField("jobInfo", jobInfo);

        }
    }


    private Site site = Site.me()
            .setCharset("gbk")
            .setTimeOut(10 * 1000)
            .setRetryTimes(3 * 1000)
            .setCycleRetryTimes(3)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

    @Override
    public Site getSite() {
        return site;
    }


    @Scheduled(initialDelay = 1000, fixedDelay = 100 * 1000)
    public void run() {
        Spider.create(new JobProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(20)
                .addPipeline(jobPipeline)
                .run();
    }
}
