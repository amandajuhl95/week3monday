

document.getElementById("btncount").onclick = countMovie;

function countMovie()
{
    fetch("/week3monday/api/movie/count")
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);

                var count = document.getElementById("count");
                count.innerText = "Count: " + data.count;

            });

}
document.getElementById("getMovie").onclick = fetchMovie;

function fetchMovie()
{
    var idOrName = document.getElementById("movie").value;
    let urlId = "/week3monday/api/movie/" + idOrName;
    let urlName = "/week3monday/api/movie/name/" + idOrName;

    if (idOrName.match(/\d+/) !== null)
    {
        url = urlId;
    } else
    {
        url = urlName;
    }

    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                var dataArray = [];
                if (data.length >= 1)
                {
                    dataArray = data;
                } else
                {
                    dataArray.push(data);
                }

                var movieList = dataArray.map(movie => "<tr><td>" + movie.movie_id + "</td><td>"
                            + movie.name + "</td><td>" + movie.release_year + "</td><td>"
                            + movie.director + "</td><td>");

                var movList = movieList.join("\n");
                document.getElementById("tablebody").innerHTML = movList;
            });
}

document.getElementById("getMovies").onclick = fetchAll;

function fetchAll()
{
    fetch("/week3monday/api/movie/all")
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);

                var movieList = data.map(movie => "<tr><td>" + movie.movie_id + "</td><td>"
                            + movie.name + "</td><td>" + movie.release_year + "</td><td>"
                            + movie.director + "</td><td></tr>");

                var movList = movieList.join("\n");
                document.getElementById("tablebody").innerHTML = movList;
            });
}





