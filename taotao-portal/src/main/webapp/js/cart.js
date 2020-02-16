var TTCart = {
	load : function(){ // 加载购物车数据
		
	},
	init : function(){
		$("[name=checkItem]").click(function () {
			//计算总价
			TTCart.refreshTotalPrice();

			//设置全选的状态
			$("[name=toggle-checkboxes]").attr("checked",$("[name=checkItem]:not(:checked)").length==0);
		});

		//全选
		$("[name=toggle-checkboxes]").click(function () {
			$("[name=checkItem]").attr("checked",this.checked);
			TTCart.refreshTotalPrice();
		});
	},
	itemNumChange : function(){
		$(".increment").click(function(){//＋
			var _thisInput = $(this).siblings("input");
			_thisInput.val(eval(_thisInput.val()) + 1);
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".action",function(data){
				TTCart.refreshTotalPrice();
			});
		});
		$(".decrement").click(function(){//-
			var _thisInput = $(this).siblings("input");
			if(eval(_thisInput.val()) == 1){
				return ;
			}
			_thisInput.val(eval(_thisInput.val()) - 1);
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".action",function(data){
				TTCart.refreshTotalPrice();
			});
		});
		$(".quantity-form .quantity-text").rnumber(1);//限制只能输入数字
		$(".quantity-form .quantity-text").change(function(){
			var _thisInput = $(this);
			$.post("/service/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".action",function(data){
				TTCart.refreshTotalPrice();
			});
		});
	},
	refreshTotalPrice : function(){ //重新计算总价
		var total = 0;
		$("[name=checkItem]:checked").each(function (i,e) {
			var _this = $(e);
			var _item = _this.parents("div.item").find(".quantity-form .quantity-text");
			total += (eval(_item.attr("itemPrice")) * 10000 * eval(_item.val())) / 10000;
		});
		// $(".quantity-form .quantity-text").each(function(i,e){
		// 	var _this = $(e);
		// 	total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
		// });
		$(".totalSkuPrice").html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
			 prefix: '￥',
			 thousandsSeparator: ',',
			 centsLimit: 2
		});
	}
};

$(function(){
	TTCart.init();
	TTCart.load();
	TTCart.itemNumChange();
});