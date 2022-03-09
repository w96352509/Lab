package com.study.springmvc.lab.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@RestController
@RequestMapping("/lab/price")
public class PriceController {	
	// 範例：symbol = ^TWII、2330.TW
	@GetMapping("/histquotes/{symbol:.+}")
	public List<HistoricalQuote> queryHistoricalQuotes(@PathVariable("symbol") String symbol) {
		Calendar from = Calendar.getInstance(); //起點
		Calendar to = Calendar.getInstance();   //現在
		from.add(Calendar.YEAR, -1); // 過去(時間)  , 前
		List<HistoricalQuote> googleHistQuotes = null; //歷史報價
		try {
			Stock google = YahooFinance.get(symbol);
			googleHistQuotes = google.getHistory(from, to, Interval.DAILY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return googleHistQuotes;
	}
	
}
