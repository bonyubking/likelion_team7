db.emp.aggregate([
	{
		$match:{
			$or:[
				{comm :{$eq:"null"}},
				{sal: {$gte : 2000}}
			]
		}
	},
	{$project:{
	_id:0,
    ename:1,
	sal:1,
	comm: {
		$cond: {if: {$eq: ["$comm", 90] }, then: "없음", else:  "$comm"}
}
}
}
	]). forEach(element => {
        print(element.ename+","+element.sal+","+element.comm)
    })
//-- 단, 커미션이 없으면 없다고 출력을 해보자.