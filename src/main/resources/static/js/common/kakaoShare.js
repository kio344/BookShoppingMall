

    Kakao.init('772b21c2ecd1910fce7350f00ed28f5a');
    console.log(Kakao.isInitialized());


function Share(bookimg,bookname,writer,publisher,price,link){
	Kakao.Share.sendCustom({
      templateId:87473 ,
      templateArgs: {
		bookimg:bookimg,
        writer:writer,
        price:price,
        bookname:bookname,
        publisher:publisher,
        link:link
      },
    });

}

    