<script type="text/javascript">
$('.toTop ').click(function(){
   $("html, body").animate({ scrollTop: 0 }, 600);
   return false;
});
$('.toBottom').click(function(){
   $('html,body').animate({scrollTop: $(document).height()}, 600);
   return false;
});
</script>
