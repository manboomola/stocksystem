package com.example.stocksystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import lombok.Data;

//商品クラス。
@Entity
@Data
public class Stock {

  //シーケンス名 
  private static final String SEQUENCE_NAME = "stock_id_seq";

  //ID
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  //商品名
  @NotBlank
  private String name;
}
