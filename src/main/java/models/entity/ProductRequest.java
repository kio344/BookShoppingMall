package models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import models.seller.product.Progress;

@Entity
public class ProductRequest extends BaseEntity {

   @Id
   @GeneratedValue
   private Long num;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "seller")
   private User seller;
   
   @Column
   private String serialnum;
   @Column(unique = true)
   private String bookName;
   @Column
   private String writer;
   @Column
   private Long price;
   @Column
   private String category;
   @Column
   private String publisher;
   @Column
   private int count;
   @Column
   @Enumerated(EnumType.STRING)
   private Progress progress = Progress.Examine;

   public Long getNum() {
      return num;
   }

   public void setNum(Long num) {
      this.num = num;
   }

   public User getSeller() {
      return seller;
   }

   public void setSeller(User seller) {
      this.seller = seller;
   }

   public String getSerialnum() {
      return serialnum;
   }

   public void setSerialnum(String serialnum) {
      this.serialnum = serialnum;
   }

   public String getBookName() {
      return bookName;
   }

   public void setBookName(String bookName) {
      this.bookName = bookName;
   }

   public String getWriter() {
      return writer;
   }

   public void setWriter(String writer) {
      this.writer = writer;
   }

   public Long getPrice() {
      return price;
   }

   public void setPrice(Long price) {
      this.price = price;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getPublisher() {
      return publisher;
   }

   public void setPublisher(String publisher) {
      this.publisher = publisher;
   }

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public Progress getProgress() {
      return progress;
   }

   public void setProgress(Progress progress) {
      this.progress = progress;
   }

   @Override
   public String toString() {
      return "Product [num=" + num + ", seller=" + seller + ", serialnum=" + serialnum + ", bookName=" + bookName
            + ", writer=" + writer + ", price=" + price + ", category=" + category + ", publisher=" + publisher
            + ", count=" + count + ", progress=" + progress + ", getRegDt()=" + getRegDt() + ", getModDt()="
            + getModDt() + "]";
   }
   
   

}