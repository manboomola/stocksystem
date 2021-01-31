package com.example.stocksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.stocksystem.model.Stock;
import com.example.stocksystem.model.StockRepository;

//商品画面のコントローラクラス。
@Controller
@RequestMapping("/stocks")
public class StockController {

  //登録/更新/削除完了後のリダイレクト先URL
  private static final String REDIRECT_URL = "redirect:/stocks/";

  //HTMLパス
  private static final String PATH_LIST = "stock/list";
  private static final String PATH_CREATE = "stock/create";
  private static final String PATH_UPDATE = "stock/update";

  // Modelの属性名
  private static final String MODEL_ATTRIBUTE_NAME = "stock";

  //商品リポジトリ
  @Autowired
  private StockRepository stockRepository;

  //一覧画面を表示。
   //@param model モデル
   //@return 遷移先
  @GetMapping(value = "/")
  public String list(Model model) {
    model.addAttribute(MODEL_ATTRIBUTE_NAME, stockRepository.findAll(Sort.by("id")));
    return PATH_LIST;
  }

  //登録画面を表示。
   //@param model モデル
   //@return 遷移先
  @GetMapping(value = "/create")
  public String create(Model model) {
    model.addAttribute(MODEL_ATTRIBUTE_NAME, new Stock());
    return PATH_CREATE;
  }

  //登録を実行。
   //@param stock 商品画面入力値
   //@param result 入力チェック結果
   //@return 遷移先
  @PostMapping(value = "/create")
  public String create(@Validated @ModelAttribute(MODEL_ATTRIBUTE_NAME) Stock stock,
      BindingResult result) {

    if (result.hasErrors()) {
      return PATH_CREATE;
    }

    stockRepository.save(stock);
    return REDIRECT_URL;
  }

  //更新画面を表示。
   //@param id 商品ID
   //@param model モデル
   //@return 遷移先
  @GetMapping(value = "/{id}/update")
  public String update(@PathVariable("id") Long id, Model model) {
    model.addAttribute(MODEL_ATTRIBUTE_NAME, stockRepository.getOne(id));
    return PATH_UPDATE;
  }

  //更新を実行。
   //@param stock 商品画面入力値
   //@param result 入力チェック結果
   //@return 遷移先
  @PostMapping(value = "/{id}/update")
  public String update(@Validated @ModelAttribute(MODEL_ATTRIBUTE_NAME) Stock stock,
      BindingResult result) {

    if (result.hasErrors()) {
      return PATH_UPDATE;
    }

    stockRepository.save(stock);
    return REDIRECT_URL;
  }

  //削除を実行。
   //@param id 商品ID
   //@return 遷移先
  @GetMapping(value = "/{id}/delete")
  public String list(@PathVariable("id") Long id) {
    stockRepository.deleteById(id);
    return REDIRECT_URL;
  }
}
