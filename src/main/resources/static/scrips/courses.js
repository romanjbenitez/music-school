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
     }
     },

     created() {          
     axios.get(`/api/courses`)
     .then(datos => {
          this.courses = datos.data
          this.filteredCourses = datos.data
          this.teachers = this.courses[0].teacher
     })
     // axios
     // .get("/api/client/current").then(api => {
     //   this.firstName = api.data.firstName
     //   this.lastName = api.data.lastName
     //   this.isLogin = true;
     // })
     axios.get('/api/teachers')
     .then(res => this.teachers = res.data)
     },
     
     mounted(){
     this.$nextTick(function () {
     this.header = document.querySelector(".nav");
     })
     },

     methods: {
     
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
          filterByPrice($event){
               if($event.target.checked){
               let lowerPrice = Number.parseInt($event.target.getAttribute('data-lower-price'))
               console.log(lowerPrice);
               let higherPrice = Number.parseInt($event.target.getAttribute('data-higher-price'));
               console.log(higherPrice);
               let result = this.courses.filter(course => course.price >= lowerPrice && course.price <= higherPrice)
               this.filteredCourses = result
               }
          },
          getAll(){
               this.filteredCourses = this.courses
          },
          getCoursesByTeacher(teacherEmail){
               let coursesByTeacher = this.courses.filter(course => course.teacher.email == teacherEmail)
               this.filteredCourses = coursesByTeacher;
               console.log(teacherEmail, coursesByTeacher);
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