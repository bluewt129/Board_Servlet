window.onload = function() {
    $("form").submit((e) => e.preventDefault());

    console.log("js onload");
    var chs = document.getElementsByName("ch");
    var chkbox = document.getElementById("chkbox");

    for (let i = 0; i < chs.length; i++) {
        chs[i].onclick = function() {
            chkbox.checked = (chs.length == chCheckedCount());
        };
    }
};

function checkAll(bool) {
    console.log("chbox의 상태", bool);
    var chs = document.getElementsByName("ch");
    for (let i = 0; i < chs.length; i++) {
        chs[i].checked = bool;
    }
}

var chCheckedCount = function() {
    var chs = document.getElementsByName("ch");
    let cnt = 0;
    for (let i = 0; i < chs.length; i++) {
        if (chs[i].checked) {
            cnt++;
        }
    }
    return cnt;
};

function chkSubmit() {
    var cnt = chCheckedCount();
    if (cnt == 0) {
        Swal.fire("선택된 글이 없습니다");
    } else {
        submitForm();
    }
    return false;
}

function chSubmit(event) {
    event.preventDefault();
    var cnt = chCheckedCount();
    if (cnt > 0) {
        Swal.fire({
            title: "정말 삭제하시겠습니까?",
            text: "복구 불가능합니다!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Delete"
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "삭제!",
                    text: "삭제되었습니다.",
                    icon: "success"
                }).then(() => {
                    submitForm();
                });
            }
        });
    } else {
        Swal.fire("선택된 글이 없습니다");
    }
}

function chacSubmit(event) {
    event.preventDefault();
    var cnt = chCheckedCount();
    if (cnt > 0) {
        Swal.fire({
            title: "가입 승인",
            text: "승인 허가 및 취소",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "승인"
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "가입승인",
                    text: "승인되었습니다",
                    icon: "success"
                }).then(() => {
                    submitForm();
                });
            }
        });
    } else {
        Swal.fire("선택된 아이디가 없습니다");
    }
}

function submitForm() {
    document.forms[0].submit();
}

function del(event) {
    event.preventDefault();
    Swal.fire({
        title: "정말 삭제하시겠습니까?",
        text: "선택된 글이 삭제됩니다.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Delete"
    }).then((result) => {
        if (result.isConfirmed) {
            document.forms[0].action = "./delete.do";
            document.forms[0].method = "post";
            document.forms[0].submit();
        } else {
            Swal.fire('삭제가 취소되었습니다');
        }
    });
}

function reply(event) {
    event.preventDefault();
    var frm = document.forms[2];
    var rcon = document.getElementById("rcon");

    if (rcon.value == "") {
        alert('내용을 입력해주세요');
    } else {
        frm.submit();
    }
}

$(document).ready(function() {
    $(document).on("click", ".replygoodcount", function(event) {
        event.preventDefault();
        var rseq = $(this).siblings("input[name=rseq]").val();
        var regoodcountElement = $(this).siblings(".regoodcount").find("b");

        $.ajax({
            url: "./updateReGood.do",
            type: "post",
            data: {
                rseq: rseq
            },
            dataType: "json",
            success: function(result) {
                if (result.result === "success") {
                    var count = parseInt(regoodcountElement.text().split(' ')[1]) + 1;
                    regoodcountElement.text("추천수 " + count);
                } else {
                    alert("처리 실패");
                }
            },
            error: function() {
                alert("오류");
            }
        });
    });

    $("#goodUpdate").click(function(event) {
        event.preventDefault();
        var seq = $("input[name=seq]").val();
        $.ajax({
            url: "./updateGood.do",
            type: "post",
            data: {
                seq: seq
            },
            dataType: "json",
            success: function(result) {
                if (result.result === "success") {
                    var count = parseInt($(".goodcount b").text().split(' ')[1]) + 1;
                    $(".goodcount b").text("추천수 " + count);
                } else {
                    alert("처리 실패");
                }
            },
            error: function() {
                alert("오류");
            }
        });
    });

    $(document).on("click", ".delreply", function(event) {
        event.preventDefault();
        var rseq = $(this).siblings("input[name=rseq]").val(); // rseq 값을 가져옴

        $.ajax({
            url: "./deleteReply.do",
            type: "post",
            data: {
                rseq: rseq
            },
            dataType: "json",
            success: function(result) {
                if (result.result === "success") {
                    alert("삭제완료");
                    $(event.target).closest('.replyBody').remove();
                } else {
                    alert("삭제 불가");
                }
            },
            error: function() {
                alert("삭제 ajax 오류");
            }
        });
    });
});
