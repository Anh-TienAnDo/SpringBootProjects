package com.ptit.product_search.entity.product;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "review")
public class Review {
    @Field(name = "points_review")
    private List<Integer> pointsReview;

    @Field(name = "average")
    private Double average;

    public void setPointsReview(List<Integer> pointsReview) {
        this.pointsReview = pointsReview;
        this.average = pointsReview.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public static void main(String[] args) {
        Review review = new Review();
        review.setPointsReview(List.of(1, 2, 3, 4, 5));
        System.out.println(review);
    }
}
