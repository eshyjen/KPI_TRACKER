window.onscroll = function()
{
    var $scrollingDiv = $("#right");
 
    $(window).scroll(function(){      
      $scrollingDiv.css('position','fixed');
      $scrollingDiv.css('top','0');          
    });
}   