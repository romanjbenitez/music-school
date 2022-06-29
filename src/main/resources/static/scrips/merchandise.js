Vue.createApp({
     data() {
          return {
               header : null,
               courses: "",
               merchandises: [],

               filteredMerch:[],

               firstName : "",
               lastName : "",
               isLogin: false,

          }
     },

     created() {
     
     axios.get(`/api/merch`)
     .then(res => {
          this.merchandises = res.data
          this.filteredMerch = res.data
     })
     axios
     .get("/api/client/current").then(api => {
       this.firstName = api.data.firstName
       this.lastName = api.data.lastName
       this.isLogin = true;
     })


     },
     
     mounted(){
     this.$nextTick(function () {
     this.header = document.querySelector(".nav");
     })

     },
     methods: {

          typeFilter(){
               let merchandisesType = this.merchandises
               let merchNew = []
               merchandisesType.forEach (merch => { 
                    if(!merchNew.includes(merch.type)){
                         merchNew.push(merch.type)
                    }  
               });
               console.log(merchNew)
          },
     
          subscribeEmail(){
               const Toast = Swal.mixin({
                    toast: true,
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                    }
               })
               
               Toast.fire({
                       icon: 'success',
                    title: 'Successfully subscribed!'
               })
               },

          filterBy($event){
               console.log($event.target.getAttribute("data-merch-type"));
               let type = $event.target.getAttribute("data-merch-type");
               let allMerch = this.merchandises
               let merchByType = allMerch.filter(product => product.type == type )
               this.filteredMerch = merchByType
               console.log(merchByType);
          },
          sortByCheapest(){
               this.filteredMerch.sort((a,b)=> a.price - b.price)
          },
          sortByMostExpensives(){
               this.filteredMerch.sort((a,b)=> b.price - a.price)
          },
          getAll(){
               this.filteredMerch = this.merchandises
          },
          

               logout() {
                    axios
                      .post("/api/logout")
                      .then((response) => window.location.replace("./index.html"));
                  },

     },
     computed: {
     headershow(){
     if( this.header != null){
          window.addEventListener("scroll", () => {
          let header = this.header
          let scrolltop = document.documentElement.scrollTop;
          let top = header.offsetTop
          if(top + 100 <= scrolltop){
               header.style.background = "#000000ff"
          }
          else{
               header.style.background = "rgba(0, 0, 0, 0.0001)"
          }
          });      
     }
     }
     },
}).mount("#app")