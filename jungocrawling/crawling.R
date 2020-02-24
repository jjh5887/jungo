final_address<-c()
final_title<-c()
final_price<-c()
data<-c()
final_data<-c()
final_img<-c()
price<-NULL
img_list<-NULL


keyword4<-""
if(str_detect(keyword," ")){
  keyword1<-str_split(keyword," ")
  keyword2<-unlist(keyword1)
  
  for(ii in 1:length(keyword2)){
    keyword3<-URLencode(keyword2[[ii]])
    keyword4<-paste0(keyword4,keyword3,"+")
    
  }
  keyword_final<-str_sub(keyword4,0,-2)
  
}else{
  keyword<-iconv(keyword,from="CP949",to="UTF-8")
  keyword_final<-URLencode(keyword)
}

for(page in 1:page_i){
  c_url<-"https://m.cafe.naver.com/ArticleSearchListAjax.nhn?search.query="
  c_url1<-paste0(c_url,URLencode(keyword_final))
  c_url2<-paste0(c_url1,"&search.menuid=0&search.searchBy=0&search.sortBy=date&search.clubid=10050146&search.option=0&search.defaultValue=&search.page=")
  c_url_final<-paste0(c_url2,page)
  b<-readLines(c_url_final,encoding = "UTF-8")
  
  url<-b[which(str_detect(b,"ArticleRead"))]
  url1<-str_extract(url,("(?<=href=).*(?=onclick)"))
  url_2<-str_sub(url1,3)
  clubid<-str_extract(url_2,("(?<=clubid=).*(?=&me)"))
  menuid<-str_extract(url_2,("(?<=menuid=).*(?=&articleid)"))
  articleid<-str_extract(url_2,("(?<=articleid=).*(?=&query)"))
  query<-str_extract(url_2,("(?<=query=).*(?=&art)"))
  art<-str_sub(str_extract(url_2,("(?<=art=).*(?=)")),,-3)
  final_link<-paste0("https://cafe.naver.com/joonggonara/",articleid)
  final_address<-c(final_address,final_link)
  
  
  title<-b[which(str_detect(b,"\t\t\t<h3"))]
  title1<-gsub("<.*?>","",title) 
  title2<-gsub("\t","",title1)
  title_final1<-gsub("&quot;","",title2) 
  title_final<-gsub("title_final1","",title_final1) 
  final_title<-c(final_title,title_final)
  
  product_url<-paste0("https://apis.naver.com/cafe-web/cafe-articleapi/cafes/",clubid,"/articles/",articleid,"?menuid=",menuid,"&query=",query,"&art=",art)
  
  
  for(i3 in 1:length(title_final)){
    cat(page, "페이지의 ",i3 ,"번째 제품 크롤링중....\n")
    remDr$navigate(product_url[i3])
    Sys.sleep(0.3)
    s_ss<-(remDr$getPageSource()[1])
    
    img<-str_extract(s_ss,("(?<=imgUrl<).*(?=saleInfo/productName)"))
    img_list[i3]<-str_extract(img,("(?<=title=\\\").*(?=\\\" draggable)"))
    if(is.na(img)){
      img<-str_split(s_ss,"dthumb-phinf")
      img1<-str_extract(img[[1]][2],("(?<=src=).*(?=\\\" draggable)"))
      img_list[i3]<-paste0("https://dthumb-phinf.pstatic.net/?src=", img1)
    }
    
    test_p1<-str_extract(s_ss,("(?<=price<).*(?=주의하세요)"))
    test_p2<-str_extract(s_ss,("(?<=price).*(?=주의하세요)"))
    
    if(!(is.na(test_p1))){
      p1<-str_extract(s_ss,("(?<=price<).*(?=주의하세요)"))
      p2<-str_extract(p1,("(?<=objectBox-number).*(?=거래전)"))
      p3<-str_sub(p2,0,56)
      p4<-str_extract(p3,("(?<=>).*(?=</span></sp)"))
      p5<-gsub(",","",p4)
      p6<-gsub(" ","",p5)
      price[i3]<-as.double(p6)
      } else if(!(is.na(test_p2))){
      p1<-str_extract(s_ss,("(?<=price).*(?=주의하세요)"))
      p2<-str_extract(p1,("(?<=:).*(?=topBanner)"))
      p3<-str_extract(p2,("(?<=).*(?=,)"))
      price[i3]<-as.double(p3)
    }else{
      p1<-str_extract(s_ss,("(?<=가격).*(?= 원&lt)"))
      p2<-str_extract(p1,("(?<=color).*(?=)"))
      p3<-str_extract(p2,("(?<=size).*(?=)"))
      p4<-str_extract(p3,("(?<=gt;).*(?=)"))
      p5<-gsub(",","",p4)
      p6<-gsub(" ","",p5)
      price[i3]<-as.double(p6)
    }
  }
  final_img<-c(final_img, img_list)
  final_price<-c(final_price, price)
  price<-NULL
  img_list<-NULL
}

rD$server$process$kill_tree()
