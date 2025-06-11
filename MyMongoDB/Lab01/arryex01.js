db.students.aggregate([
  {
    $project: {
      name: 1,
      grades: {
        $filter: {
          input: "$grades",
          as: "g",
          cond: {
            $and: [
              { $eq: ["$$g.subject", "수학"] },
              { $gte: ["$$g.score", 90] }
            ]
          }
        }
      }
    }
  },
  {
    $match: {
      grades: { $ne: [] }
    }
  }
]).forEach(element => {
    print(element)
});



