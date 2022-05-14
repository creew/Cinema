$(document).ready(function () {
    update('')
    $("#live_search").keyup(function () {
        const input = $(this).val();
        update(input)
    })
})

function update(val) {
    $.ajax({
        url: "/sessions/search",
        method: "GET",
        data: {filmName: val},
        success: function (data) {
            $("#sessions tr").remove()
            for (const i in data) {
                const row$ = $('<tr/>')
                row$.append($('<td/>')
                    .append($('<img>')
                        .attr("src", "/images/" + (data[i].film.poster != null ? data[i].film.poster.id : "11111111-1111-1111-1111-111111111111"))
                        .attr("alt", "poster")
                        .attr("width", "100")
                        .attr("height", "100")
                    )
                )
                row$.append($('<td/>').html(data[i].date))
                row$.append($('<td/>').append($('<a>').attr("href", "/sessions/" + data[i].id).html(data[i].film.title)))
                $("#sessions").append(row$)
            }
        }
    })
}