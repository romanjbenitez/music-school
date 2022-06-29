Vue.createApp({
     data() {
     return {
     charging: true,
     hidden: "",
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
     .then(res => {
          this.courses = res.data
          this.filteredCourses = res.data
     })
     axios
     .get("/api/client/current").then(api => {
       this.firstName = api.data.firstName
       this.lastName = api.data.lastName
       this.isLogin = true;
     }).catch(err => null)
     axios.get('/api/teachers')
     .then(res => this.teachers = res.data)
     setTimeout(() => { this.charging = false }, 1500)
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
          filterBy($event){
               let filter = [];
               let byLevel = this.filterByLevel($event);
               let byInstr = this.filterByInstrument($event);
               let byPrice = this.filterByPrice($event);



               if(byLevel.length > 0){
                    byLevel.forEach(course => !filter.includes(course) && filter.push(course))
               }
               if(byInstr.length > 0){
                    byInstr.forEach(course => !filter.includes(course) && filter.push(course))
               }
               if(byPrice.length > 0){
                    byPrice.forEach(course => !filter.includes(course) && filter.push(course))
               }
               this.filteredCourses = filter;
            
          
          },
         filter(){

         },
          filterByLevel($event){

               let attr = $event.target.getAttribute('data-filter-by')
               let filteredByLevel = [];
               filteredByLevel = this.courses.filter(course => course.level == attr )
               return filteredByLevel

          }, 
          filterByInstrument($event){
               let attr = $event.target.getAttribute('data-filter-by')
               let filteredByInstr = this.courses.filter(course => course.name == attr)
               return filteredByInstr;
          },
          filterByPrice($event){
             
               let lowerPrice = Number.parseInt($event.target.getAttribute('data-lower-price'))
               console.log(lowerPrice);
               let higherPrice = Number.parseInt($event.target.getAttribute('data-higher-price'));
               console.log(higherPrice);
               let result = this.courses.filter(course => course.price >= lowerPrice && course.price <= higherPrice)
               return result
            
          },
          filterByTeacher(teacherEmail){
               let coursesByTeacher = this.courses.filter(course => course.teacher.email == teacherEmail)
               this.filteredCourses = coursesByTeacher;
          },
          getAll(){
               this.filteredCourses = this.courses
          },

          getCoursesByTeacher(teacherEmail){
               let coursesByTeacher = this.courses.filter(course => course.teacher.email == teacherEmail)
               this.filteredCourses = coursesByTeacher;
               console.log(teacherEmail, coursesByTeacher);
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
     },

     },
}).mount("#app")