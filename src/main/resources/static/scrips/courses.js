Vue.createApp({
     data() {
     return {
     header : null,
     courses: "",
     filteredCourses: [],
     teachers:[],
     priceRange:[],
     filteredByPrice: [],
     firstName : "",
     lastName : "",
     isLogin: false,

     coursescart:[],
     courseId:[],
     coursesInStorage:[],

     merchscart:[],
     merchId:[],
     merchsInStorage:[],

     }
     },

     created() {          
     axios.get(`http://localhost:8080/api/courses`)
     .then(datos => {
          this.courses = datos.data
          this.filteredCourses = datos.data
          this.teachers = this.courses[0].teacher

          this.coursesInStorage = JSON.parse(localStorage.getItem("cartCourse"))
          if(this.coursesInStorage != null){
               this.coursescart = this.coursesInStorage
          }

     })
     axios.get(`http://localhost:8080/api/merch`)
     .then(datos => {
          this.merchandises = datos.data

          this.merchsInStorage = JSON.parse(localStorage.getItem("cartMerch"))
          if(this.merchsInStorage != null){
               this.merchscart = this.merchsInStorage
          }

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

          cartMerchs(merch){
               this.merchId = this.merchscart.map(merch1 => merch1.id)
               if(!this.merchId.includes(merch.id) && merch.stock > 0){
                    merch.stock -= 1
                    merch.unidadesAComprar = 1
                    this.merchscart.push(merch)
                    localStorage.setItem("cartMerch",JSON.stringify(this.merchscart))
               }
          },
          
          cartCourses(course){
               this.courseId = this.coursescart.map(course1 => course1.id)
               if(!this.courseId.includes(course.id)){
                    this.coursescart.push(course)
                    localStorage.setItem("cartCourse",JSON.stringify(this.coursescart))
               }
          },

          deleteCartCourse(course){
               this.coursesInStorage = this.coursesInStorage.filter(course1 => course1.id != course.id)
               this.coursescart = this.coursesInStorage
               localStorage.setItem("cartCourse",JSON.stringify(this.coursesInStorage))
          },

         

          deleteCartMerchs(merch){
               this.merchsInStorage = this.merchsInStorage.filter(merch1 => merch1.id != merch.id)
               this.merchscart = this.merchsInStorage
               localStorage.setItem("cartMerch",JSON.stringify(this.merchsInStorage))
          },

          aumentarUnidadesAComprar(merch){
               if ((merch.stock - merch.unidadesAComprar) > -1) {
                    merch.unidadesAComprar++
                  }
          },

          disminuirUnidadesAComprar(merch){
               if (merch.unidadesAComprar > 0) {
                    merch.unidadesAComprar--
                  }
          },

          calcularSubtotalMerch(merch) {
               return merch.price * merch.unidadesAComprar
             },
             calcularSubtotalCourse(course) {
               return course.price
             },
             obtenerPrecioTotal() {
               let precioTotal = 0
               this.merchscart.forEach(producto => precioTotal += this.calcularSubtotalMerch(producto))
               this.coursescart.forEach(course => precioTotal += this.calcularSubtotalCourse(course))
               return precioTotal
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
          filterByLevel($event){
               let attr = $event.target.getAttribute('data-filter-by')
               let filteredByLevel = this.courses.filter(course => course.level == attr || course.name == attr )
               this.filteredCourses = filteredByLevel;
               console.log(attr);
               console.log(filteredByLevel);

          },     
          // filterByPrice($event){
          //      console.log($event.target.checked);
          //      if($event.target.checked){
          //      let lowerPrice = Number.parseInt($event.target.getAttribute('data-lower-price'))
          //      console.log(lowerPrice);
          //      let higherPrice = Number.parseInt($event.target.getAttribute('data-higher-price'));
          //      console.log(higherPrice);
          //      let result = this.courses.filter(course => course.price > lowerPrice && course.price < higherPrice)
          //      // let result = this.filteredCourses.lenght > 0 ? this.filteredCourses.filter(course => course.price > lowerPrice && course.price < higherPrice) : this.courses;
          //      console.log(result);
          //      // this.filteredCourses = result
          //      return result;
          //      }
          // },
          price($event){

               let coursesByPrice= [];
               if(this.priceRange.length > 0){
               this.priceRange.forEach(range => {
                    if(range == '0-10k')
                    coursesByPrice.push(this.courses.filter(course => course.price <= 10000))  
                    
                    if(range == '10k-20k')
                    coursesByPrice.push(this.courses.filter(course => course.price >= 10000 && course.price <= 20000))                   
                    
                    if(range == '20k-30k')
                    coursesByPrice.push(this.courses.filter(course => course.price >= 20000 && course.price <= 30000))                   
                    // if(range == '30k-40k')
                    //      coursesByPrice.push(this.courses.filter(course => course.price >= 20000 && course.price <= 30000))
                    // if(range == '40k-50k')
                    //      coursesByPrice.push(this.courses.filter(course => course.price >= 30000 && course.price <= 40000))
                    
                         
                    })
                    console.log('precio:',this.priceRange);
                    console.log(coursesByPrice.flat());
     //                
                         
                         
          
          }
     }
     

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
     },
     // filterByPrice($){
          // this.priceRange.forEach(price => { 
          //       if(price == '0-10k'){
          //           this.filteredByPrice = this.courses.fiterRange
          //       }
               
               // switch(element){
               //      case '0-10k':
               //      this.filteredByPrice += this.courses.filter(course => course.price <= 10000);
                    
               //      case '10k-20k':
               //      this.filteredByPrice += this.courses.filter(course => course.price >= 10000 && course.price <= 20000 );
               
               //      case '20k-30k':
               //        this.filteredByPrice += this.courses.filter(course => course.price >= 20000 && course.price <= 30000 );
               
               //      case '30k-40k':
               //           this.filteredByPrice = this.courses.filter(course => course.price >= 30000 && course.price <= 40000 );
               // }
          
          // });
     // }
     },
}).mount("#app")