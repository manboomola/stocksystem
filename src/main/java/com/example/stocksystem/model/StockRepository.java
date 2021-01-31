package com.example.stocksystem.model;

import org.springframework.data.jpa.repository.JpaRepository;

//商品リポジトリクラス
public interface StockRepository extends JpaRepository<Stock, Long> {

}