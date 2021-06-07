package com.lgd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

  private String id;
  private String cateName;
  private String level;
  private String parentId;

  private List<Category> cates;
  private List<Video> videos;

}
