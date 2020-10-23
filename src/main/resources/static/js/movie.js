
// function loadHeadAndRoot() {
//     $("#head").load("../common/head.html")
//   };

//加载电影
function loadMovieList(areaId,movieTypeId){
    $.ajax({
        url:"http://localhost:8081/movies",
        type:"GET",
        dateType:"json",
        data:{areaId:areaId,movieTypeId:movieTypeId,limit:20},
        success:function(resp){
            if(resp.code ==0){
                $("#msg").html("共有"+resp.count+"条数据");
                var movieList="";
                var movie="";
                console.log(resp);
                for(var i=0;i<resp.data.length;i++){

                    movie=`<div class="layui-col-md2 layui-col-sm4 movie-list-item">
                                     <a href="movie-detail.html?id=`+resp.data[i].id+`">
                                        <img id="poster" src="`+resp.data[i].poster+`"> 
                                        <i id="score" class="layui-icon layui-icon-rate">`+resp.data[i].score+`</i>                                    
                                        <p id=name >`+resp.data[i].name+`</p>                                                                                   
                                     </a>           
                                    </div> `;
                    movieList+=movie;
                }
                $("#movie-list-row").append(movieList);
            }else{
                $("#msg").append(resp.msg);
            }
        }
    });
};
//加载类型
function loadMovieType(){
    $.ajax({
        url:"http://localhost:8081/movieTypes",
        type:"GET",
        dataType:"json",
        success:function(resp){
            if(resp.code==0){
                var typeList="";
                for(var i=0;i<resp.data.length;i++){
                    typeList+=`
                                    <button id=`+resp.data[i].id+`>`+resp.data[i].name+`</button>
                                    `
                }
                $("#movieType-item").html(typeList);
            }else{
                $("#movieType-item").html(resp.msg);
            }
        }
    })
}
//下载电影
function downloadMovie(path){
    $.ajax({
        url:"/fileDownload",
        data: path,
        dataType: "json",
        success: function (resp) {
            layer.msg(resp.msg)
        }

    })
}

