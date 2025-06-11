print("1. 전체 출력 ");
db.Product.find()
.forEach(printjson);

print("2. 아이디와 가격 출력");

db.Product.find({}, {_id: 1, Price: 1})
.forEach(printjson);

print("3. 가격 출력");

db.Product.find({}, {_id: 0, Price: 1})
.forEach(printjson);

print("4. 이름과 가격 출력");

db.Product.find({}, {_id: 0, Name: 1,Price: 1})
.forEach(printjson);

print("5. 가격과 카테고리만 출력")

db.Product.find({}, {_id: 0, Category: 1, Price: 1})
.sort({Price: 1})
.forEach(printjson);

print("5. 가격과 이름 출력 / 목록 내림차순")

db.Product.find({}, {_id: 0, Name: 1, Price: 1})
.sort({Name: -1})
.forEach(printjson);


print("6. 이름 출력하는데 별칭줘서 하기")

db.Product.aggregate([
    { $project: {_id: 0, alias_name: "$Name"}}
])
.forEach(printjson);

print("7.Name을 목록, Price 가격, Category는 타입으로 별칭을 주고 출력하자 ")

db.Product.aggregate([
    { $project: {_id: 0, 목록: "$Name", 가격: "$Price", 타입: "$Category"}}
])
.forEach(printjson);

print("  8.상품의 목록(Name)과 inc_price라는 별칭을 주고 Price에 100을 더하자")

db.Product.aggregate([
    { $project: {_id: 0, 목록: "$Name", inc_price: { $add: ["$Price",100]}}}
])
.forEach(printjson);

print("9. 카테고리로 그룹화를 한 다음 최대 가격을 출력 해보자.")

db.Product.aggregate([
    { $group: {_id: "$Category", max_price: { $max : "$Price"}} }
])
.forEach(printjson);

print("10 . 카테고리로 그룹화를 한 다음 최소 가격을 출력 해보자.")

db.Product.aggregate([
    { $group: {_id: "$Category", min_price: { $min : "$Price"}} }
])
.forEach(printjson);

print("11.상품 목록을 출력하고 그룹별 가격의 합과 가격의 평균과 목록의 개수를 구하자.")

db.Product.aggregate([
    { $group: {_id: "$Category", sum_price: {$sum: "$Price"}, avg:{$avg: "$Price"}, count: {$sum: 1}} }
])
.forEach(printjson);

print("12.상품목록을 출력하고 그룹화 한 다음 개수를 구해보자 ")

   db.Product.aggregate([ 
      {$group:{_id:"$Category", count:{$sum:1}}},
      {$project:{_id: 0, 카테고리:"$_id", count:1}}
       
     ]).forEach(printjson); 
      
   db.Product.aggregate([ 
      {$project: {_id: 0,"Category": 1,count:{ $literal: 1 }}} 
    ]).forEach(printjson);

print("13. Name에서 bread를 찾아 출력 하자.")

db.Product.aggregate([
    {$project: {_id: 0, Name: 1, Category: 1}},
    {$match: {Name: "bread"}}
]).forEach(printjson);

print("14 Category가 Food인놈만")

db.Product.aggregate([
    {$project: {_id: 0, Name: 1, Category: 1}},
    {$match: {Category: "food"}}
]).forEach(printjson);

print("15. Category의 food의 가격의 최대값, 최소값, 총합, 평균, 개수를 출력 하자.")

db.Product.aggregate([
    {$match: {Category: "food"}},
    {$group: {_id: "$Category", 최대값 : {$max: "$Price"},
                                최소값 : {$min: "$Price"},
                                총합: {$sum: "$Price"},
                                평균값: {$avg: "$Price"},
                                개수: {$sum: 1}}}

]).forEach(printjson);

print("Q1.모든 제품 중에서 가장 높은 가격을 찾아서 리턴하자.")

db.Product.aggregate([
    {$group: {_id: 0, 가장높은가격 : {$max: "$Price"}}},
    {$project: {_id: 0, Name: 1, Category: 1, 가장높은가격: 1}}
]).forEach(printjson);

print("Q2)가격이 100보다 큰 상품의 개수를 출력 하자.")

db.Product.aggregate([
    { $match: { Price: { $gt: 100 } } },
    { $group: { _id: 0, 개수: {$sum: 1 } } },
    { $project: { _id: 0, 개수: 1 } }
]).forEach(printjson);

print("◼ Q3)  name이 문자 S로 시작하는 제품의 총 가격을 계산하자.")

db.Product.aggregate([
    { $match: { Name: { $regex: /^S/i } } },
    { $group: { _id: 0, 총가격: {$sum: "$Price"}}},
    { $project: { _id: 0, 총가격: 1}}
]).forEach(printjson);

db.Product.aggregate([
    { $match: { Name: { $regex: /^S/i } } },
    { $project: { _id: 0, Name: 1, Category: 1}}
]).forEach(printjson);

print("◼ Q4)  Category의 meterial의 평균 가격을 출력하자.")

db.Product.aggregate([
    { $match: { Category: "material"} },
    { $group: { _id: "$Category", 평균가격: {$avg: "$Price" }}},
    { $project: { _id: 0, Category: "$_id", 평균가격: 1}}
]).forEach(printjson);

print("◼ Q5) material 있는 모든 제품의 총 가격을 계산하되 가격이 50보다 큰 제품만 포함"
+ "한다.  또한 제품 가격이 150보다 큰 경우 총 가격에 10% 할인을 포함한다.")

db.Product.aggregate([
    { $match: { 
        $and:[
            {Category: "material"},
            {Price: {$gt: 50} }
        ]
    }
    },
    { $group: {_id: 0, 총가격: {$sum: {$cond: {
            if: { $gt: ["$Price", 150] },
            then: { $multiply: ["$Price", 0.9] },  
            else: "$Price"
          }
    }

    }
    }
},
    {$project: { _id: 0, 총가격: 1}}

]).forEach(printjson);
