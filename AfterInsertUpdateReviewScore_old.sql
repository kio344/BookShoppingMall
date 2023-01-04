CREATE DEFINER=`root`@`%` TRIGGER `AfterInsertUpdateReviewScore` AFTER INSERT ON `ProductReview` FOR EACH ROW BEGIN
	DECLARE avgScore DOUBLE;
    DECLARE productNum BIGINT;
    
	SET productNum = (SELECT b.product_num 
					FROM ProductReview a, Payment b 
                    WHERE a.num = new.num AND a.payment_num = b.num);

    SET avgScore = (SELECT AVG(score) FROM ProductReview a, Payment b 
						WHERE a.payment_num = b.num AND b.product_num = productNum);
    
	UPDATE ProductRequest 
		SET score = avgScore
	WHERE num = productNum;
END