 var app = angular.module('CApp', ['angularUtils.directives.dirPagination', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngStorage']);

// var sideshow = true;
counter = 0;

app.controller('TopbarCtrl', function($scope,$rootScope){
  $scope.hideSidebar = function(){
    var sidebar = angular.element(document.querySelector("#sidebar"));
    var mainblock = angular.element(document.querySelector("#ShowBlock"));
    if (!(counter%2)) //first hide
    {
      sidebar.css({"display": "none"});
      mainblock.css({"margin-left":"auto", "margin-top":"60px"})
      counter++;
    }
    else
    {
      sidebar.css({"display":""});
      mainblock.css({"margin-left":"12%", "margin-top":"60px"})
      counter++;
    }
    
  };

});

app.controller('SideCtrl' ,function($scope,$rootScope){
    
    $scope.showComStar = function(id){
      $rootScope.$broadcast('InitCommiteeStar', id);
    };

});

app.controller('LegiCtrl', function($scope, $http, $rootScope){

	 $scope.pageSize = 10;
 	 $scope.HouseShow = false;
   $scope.SenateShow =false;
   $scope.StateShow = true;
   $scope.TempId = "";

   $scope.getLegislator = function(object)
   {

    $rootScope.$broadcast('SearchLegislator', object);

    $scope.bioId = object.bioguide_id;
    $scope.Legislator = object;
    $scope.termnow = new Date();

    $scope.termstart = new Date(Date.parse(object.term_start.replace(/-/g, "/")));
    $scope.termend = new Date(Date.parse(object.term_end.replace(/-/g, "/")));
    $scope.now = Number($scope.termnow);
    $scope.start = Number($scope.termstart);
    $scope.end = Number($scope.termend);

    $scope.term = Math.round(($scope.now-$scope.start)/($scope.end-$scope.start) * 100);

      $http({
      method:"GET",
      url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
      params:{bID:$scope.bioId}
      })
      .then(function(response){
        $scope.LegisBillinfo = response.data;
      });

      $http({
      method:"GET",
      url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
      params:{cID:$scope.bioId}
      })
      .then(function(response){
        $scope.LegisComminfo = response.data;
      });
   };

   $scope.legislatorSave = function(object){    
    var star = angular.element(document.querySelector("#LegiStar"));
    if (star.css("color")!= "rgb(252, 244, 7)") 
    {
        $rootScope.$broadcast('SaveLegislator', object);
    }
    else
    {
        $rootScope.$broadcast('DeleteLegislator', object);
    }

   };

     $scope.statesdata = {
      avaiableOptions :[
      {name:'All States', value:'!All States'},
      {name:'American Samoa', value:'American Samoa'},{name:'Alabama', value:'Alabama'},{name:'Alaska', value:'Alaska'},{name:'Arizona', value:'Arizona'},{name:'Arkansas', value:'Arkansas'},
      {name:'California', value:'California'},{name:'Colorado', value:'Colorado'},{name:'Connecticut', value:'Connecticut'},{name:'Delaware', value:'Delaware'},{name:'District of Columbia', value:'District of Columbia'},
      {name:'Florida', value:'Florida'},{name:'Georgia', value:'Georgia'},{name:'Guam', value:'Guam'},{name:'Hawaii', value:'Hawaii'},{name:'Idaho', value:'Idaho'},
      {name:'Illinois', value:'Illinois'},{name:'Indiana', value:'Indiana'},{name:'Iowa', value:'Iowa'},{name:'Kansas', value:'Kansas'},{name:'Kentucky', value:'Kentucky'},
      {name:'Louisiana', value:'Louisiana'},{name:'Maine', value:'Maine'},{name:'Maryland', value:'Maryland'},{name:'Massachusetts', value:'Massachusetts'},
      {name:'Michigan', value:'Michigan'},{name:'Minnesota', value:'Minnesota'},{name:'Mississippi', value:'Mississippi'},{name:'Missouri', value:'Missouri'},{name:'Montana', value:'Montana'},{name:'Nebraska', value:'Nebraska'},
      {name:'Nevada', value:'Nevada'},{name:'New Hampshire', value:'New Hampshire'},{name:'New Jersey', value:'New Jersey'},{name:'New Mexico', value:'New Mexico'},{name:'New York', value:'New York'},
      {name:'North Carolina', value:'North Carolina'},{name:'North Dakota', value:'North Dakota'},{name:'Northern Mariana Islands', value:'Northern Mariana Islands'},{name:'Ohio', value:'Ohio'},{name:'Oklahoma', value:'Oklahoma'},
      {name:'Oregon', value:'Oregon'},{name:'Pennsylvania', value:'Pennsylvania'},{name:'Puerto Rico', value:'Puerto Rico'},{name:'Rhode Island', value:'Rhode Island'},{name:'South Carolina', value:'South Carolina'},
      {name:'South Dakota', value:'South Dakota'},{name:'Tennessee', value:'Tennessee'},{name:'Texas', value:'Texas'},{name:'Utah', value:'Utah'},{name:'Vermont', value:'Vermont'},
      {name:'Virginia', value:'Virginia'},{name:'US Virgin Islands', value:'US Virgin Islands'},{name:'Washington', value:'Washington'},{name:'West Virginia', value:'West Virginia'},{name:'Wisconsin', value:'Wisconsin'},
      {name:'Wyoming', value:'Wyoming'}
      ],
      selectedOption:{name:'All States', value:'!All States'}
     };

      $scope.editShowValue = function(id){
      if (id == 'state')
      {
        $scope.HouseShow = false;
        $scope.SenateShow = false;
        $scope.StateShow = true;
      }
      else if (id == 'house') 
      {
        $scope.HouseShow = true;
        $scope.SenateShow = false;
        $scope.StateShow = false;
      }
      else if (id == 'senate')
      {
        $scope.HouseShow = false;
        $scope.SenateShow = true;
        $scope.StateShow = false;
      }
    };

	$http({
    	method: "GET",
    	url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
    	params:{lt:1}
    })
    .then(function(response){
    	$scope.LegisResouceAll = response.data.results;
    });

    $http({
      method:"GET",
      url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
      params:{lt:2}
    })
    .then(function(response){
      $scope.LegisResouceHouse = response.data.results;
    });

    $http({
      method:"GET",
      url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
      params:{lt:3}
    })
    .then(function(response){
      $scope.LegisResouceSenate = response.data.results;
    });    

});

app.controller('BillCtrl', function($scope, $http, $rootScope, $sce){

		 $scope.ActiveShow = true;
		 $scope.NewShow = false;
     $scope.TempbillID = "";
     

     $scope.getBill = function(object){
        $rootScope.$broadcast('SearchBill', object);
        $scope.Billinfo = object;
        $scope.BillPdf = $sce.trustAsResourceUrl(object.last_version.urls.pdf);
     };

		 $scope.editBillValue = function(id){
		 	if(id == 'active')
		 	{
		 		$scope.ActiveShow = true;
		 		$scope.NewShow = false;
		 	}
		 	else if (id == 'new') 
		 	{
		 		$scope.ActiveShow = false;
		 		$scope.NewShow = true;
		 	}
		 };

    $scope.billSave = function(object){
      var star = angular.element(document.querySelector("#BillStar"));
      if (star.css("color")!= "rgb(252, 244, 7)")   //add bill into favorite
      {
          $rootScope.$broadcast('SaveBill', object);
      }
      else
      {
        $rootScope.$broadcast('DeleteBill', $scope.Billinfo);
      }

    };

		 $http({
		 	    method:"GET",
      		url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
      		params:{bt:1}
		 })
		 .then(function(response){
		 	$scope.ActiveBills = response.data.results;
		 });

		 $http({
		 	    method:"GET",
      		url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
      		params:{bt:2}
		 })
		 .then(function(response){
		 	$scope.NewBills = response.data.results;
		 });
});

app.controller('CommCtrl', function($scope, $http, $rootScope){
  $scope.commhouseShow = true;
  $scope.commsenateShow = false;
  $scope.commjointShow = false;
  $scope.commTempID = "";

  $scope.editCommValue = function(id){
    if (id =='house') 
    {
      $scope.commhouseShow = true;
      $scope.commsenateShow = false;
      $scope.commjointShow = false;
    }
    else if (id == 'senate') 
    {
      $scope.commhouseShow = false;
      $scope.commsenateShow = true;
      $scope.commjointShow = false;
    }
    else if (id == 'joint') 
    {
      $scope.commhouseShow = false;
      $scope.commsenateShow = false;
      $scope.commjointShow = true;
    }
  };

  $scope.commSave = function(object){
    var comid = "#" + object.committee_id;
    var star = angular.element(document.querySelector(comid));
    if (star.css("color")!= "rgb(252, 244, 7)") 
    {
      
      $rootScope.$broadcast('SaveComm', object);
    }
    else
    {
      $rootScope.$broadcast('DeleteComm', object);
    }
    
  };

  $http({
    method:"GET",
    url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
    params:{ct:1}
  })
  .then(function(response){
    $scope.CommsHouse = response.data.results;
  });

  $http({
    method:"GET",
    url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
    params:{ct:2}
  })
  .then(function(response){
    $scope.CommsSenate = response.data.results;
  });

  $http({
    method:"GET",
    url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
    params:{ct:3}
  })
  .then(function(response){
    $scope.CommsJoint = response.data.results;
  });

});

app.controller('FavCtrl', function($scope, $rootScope, $http, $sce, $localStorage){

  $scope.flegislatorShow = true;
  $scope.fbillShow = false;
  $scope.fcommShow = false;

  $scope.init = function()
  {
    if (!$localStorage.localLegislators)
     {
        $scope.flegislators = $localStorage.localLegislators = [];
     }
     else
      $scope.flegislators = $localStorage.localLegislators;

    if (!$localStorage.localBills) 
    {
      $scope.fbills = $localStorage.localBills = [];
    }
    else
      $scope.fbills = $localStorage.localBills;
    
    if (!$localStorage.localComms) 
    {
      $scope.fcomms = $localStorage.localComms = [];
    }
    else
    {
      $scope.fcomms = $localStorage.localComms;
    }
  };


  // $scope.flegislators = $localStorage.flegislators;

  $scope.editFavValue = function(type){
    if (type=='flegislator') 
    {
      $scope.flegislatorShow = true;
      $scope.fbillShow = false;
      $scope.fcommShow = false;
    }
    else if (type=='fbill') 
    {
      $scope.flegislatorShow = false;
      $scope.fbillShow = true;
      $scope.fcommShow = false;
    }
    else if (type=='fcomm') 
    {
      $scope.flegislatorShow = false;
      $scope.fbillShow = false;
      $scope.fcommShow = true;
    }
  };

  // $scope.flegislators = [];
  // $scope.fbills = [];
  // $scope.fcomms = [];


  $scope.$on('InitCommiteeStar', function(event,data){
     for (var i = 0; i < $scope.fcomms.length; i++) 
      {
        var tempCid = "#" + $scope.fcomms[i].committee_id;
        var initStar = angular.element(document.querySelector(tempCid));
        initStar.css({"color": "#fcf407", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
      } 
      console.log(data);
  });

  $scope.$on('SaveLegislator', function(event, data){

    console.log(data);
    var TempStar = angular.element(document.querySelector("#LegiStar"));
    TempStar.css({"color": "#fcf407", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
    $scope.flegislators.push(data);
    // console.log($scope.flegislators);
    $localStorage.localLegislators = $scope.flegislators;
  });

  $scope.$on('DeleteLegislator', function(event, data){
    console.log(data);
    $scope.deletelegislator(data.bioguide_id);
  });

  $scope.$on('SearchLegislator', function(event,data){
    var TempStar = angular.element(document.querySelector("#LegiStar"));
    for (var i = 0; i < $scope.flegislators.length; i++) 
    {
      if ($scope.flegislators[i].bioguide_id==data.bioguide_id) 
      {
        console.log("legislator exist!");
        TempStar.css({"color":"#fcf407", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
        return;
      }
    }
    console.log("legislator not found!");
    TempStar.css({"color":"#ffffff", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
  })

  $scope.$on('SaveBill', function(event, data){
    console.log(data);
    var TempStar = angular.element(document.querySelector("#BillStar"));
    TempStar.css({"color": "#fcf407", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
    $scope.fbills.push(data);
    $localStorage.localBills = $scope.fbills;
    // console.log($scope.fbills);
    console.log("Bill Save Successfully!");
  });

  $scope.$on('DeleteBill', function(event, data){
    console.log(data);
    $scope.deletebill(data.bill_id);
  });

  $scope.$on('SearchBill', function(event, data){
    // var isSave = $scope.fbills.indexOf(data);
    var Tempstar = angular.element(document.querySelector("#BillStar"));
    for (var i = 0; i < $scope.fbills.length; i++)
    {
      if ($scope.fbills[i].bill_id==data.bill_id)
       {  
          console.log("Bill find!");
          Tempstar.css({"color":"#fcf407", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
          return;
       }
    }
    console.log("Bill not fond!");
    Tempstar.css({"color":"#ffffff", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
  })

  $scope.$on('SaveComm', function(event, data){
    console.log(data);
    var Tempid = "#" + data.committee_id;
    (angular.element(document.querySelector(Tempid))).css({"color": "#fcf407", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
    $scope.fcomms.push(data);
    $localStorage.localComms = $scope.fcomms;
    // console.log($scope.fcomms);
    console.log("Save Committee Successfully!");
  });

  $scope.$on('DeleteComm', function(event, data){
    console.log(data);
    $scope.deletecomm(data.committee_id);
  });

  $scope.deletelegislator = function(id){
    for (var i = 0; i < $scope.flegislators.length; i++) {
      if ($scope.flegislators[i].bioguide_id==id)
       {
        $scope.flegislators.splice(i,1);
        $localStorage.localLegislators = $scope.flegislators;
        console.log($scope.flegislators);
        console.log("Delete Legislator Successfully!");
       }
    }
    var TempStar = angular.element(document.querySelector("#LegiStar"));
    TempStar.css({"color": "#ffffff", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
   };

   $scope.deletebill = function(id){
    console.log("Delete Bill!");
    for (var i = 0; i < $scope.fbills.length; i++) {
      if ($scope.fbills[i].bill_id==id) 
        {
           $scope.fbills.splice(i,1);
           $localStorage.localBills = $scope.fbills;
          console.log($scope.fbills);
        }
    }
      var Tempstar = angular.element(document.querySelector("#BillStar"));
      Tempstar.css({"color":"#ffffff", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
      console.log("Bill Delete Successfully!")
   };

   $scope.deletecomm = function(id){
    console.log("Delete Committee!");
    for (var i = 0; i < $scope.fcomms.length; i++) {
      if ($scope.fcomms[i].committee_id==id) 
      {
        $scope.fcomms.splice(i,1);
        console.log("Delete Committee Successfully!");
        $localStorage.localComms = $scope.fcomms;
      }
    }
    var tempid = "#" + id;
    (angular.element(document.querySelector(tempid))).css({"color":"#ffffff", "-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
   };


  $scope.getLegislator = function(object)
   {
    $scope.bioId = object.bioguide_id;
    $scope.Legislator = object;
    $scope.termnow = new Date();

    $scope.termstart = new Date(Date.parse(object.term_start.replace(/-/g, "/")));
    $scope.termend = new Date(Date.parse(object.term_end.replace(/-/g, "/")));
    $scope.now = Number($scope.termnow);
    $scope.start = Number($scope.termstart);
    $scope.end = Number($scope.termend);

    $scope.term = Math.round(($scope.now-$scope.start)/($scope.end-$scope.start) * 100);

      $http({
      method:"GET",
      url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
      params:{bID:$scope.bioId}
      })
      .then(function(response){
        $scope.LegisBillinfo = response.data;
      });

      $http({
      method:"GET",
      url:"http://biaoge1-env.us-west-2.elasticbeanstalk.com/",
      params:{cID:$scope.bioId}
      })
      .then(function(response){
        $scope.LegisComminfo = response.data;
      });
   };

   $scope.getBill = function(object){

        $scope.Billinfo = object;
        $scope.BillPdf = $sce.trustAsResourceUrl(object.last_version.urls.pdf);
   };


});

// pagination controller
 function Pagi2Controller($scope) {
  $scope.pageChangeHandler = function(num) {
    console.log('going to page ' + num);
  };
};

app.controller('Pagi2Controller', Pagi2Controller);