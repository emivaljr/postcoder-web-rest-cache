$("#button1id").click(function () {
  var urlServer = $("#serveraddressinput").val()+'/'+$('input[name=radio-lookup-service]:checked').val()+'/'+$('input[name=radio-address-type]:checked').val();
  if($('input[name=radio-lookup-service]:checked').val() == 'rgeo'){
    urlServer += '/'+$("#latitudeinput").val()+'/'+$("#longitudeinput").val();
  }else{
      urlServer += '/'+$("#searchaddressinput").val();
  }
  var fields =['lines','include','exclude','identifier','distance'];
  var paramJson = {};
  for (var i = 0; i < fields.length; i++) {
    var field = fields[i];
    if($("#"+field+"input").val() !== ''){
      paramJson[field] = $("#"+field+"input").val();
    }
  }
  paramJson['format'] = $('input[name=radio-result-format]:checked').val();
  $('#result').html('');
  $.ajax({
      url: urlServer,
      data: paramJson
  }).then(function(data) {

    $('<div/>').text(data).appendTo('#result');
  });
});
$('input[type=radio][name=radio-address-type]').on('change', function() {
  $("input[name=radio-lookup-service][value='address']").prop("checked",true);
  $(".div-rgeo").css("display","none");
  $(".div-search-address").css("display","");
     switch($(this).val()) {
         case 'uk':
             $(".radio-irish-service").css("display","none");
             break;
         case 'ie':
             $(".radio-irish-service").css("display","");
             break;
     }
});
$('input[type=radio][name=radio-lookup-service]').on('change', function() {
     switch($(this).val()) {
         case 'rgeo':
             $(".div-rgeo").css("display","");
             $(".div-search-address").css("display","none");
             break;
         default:
             $(".div-rgeo").css("display","none");
             $(".div-search-address").css("display","");
             break;
     }
});
