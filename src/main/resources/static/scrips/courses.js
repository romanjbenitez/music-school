Vue.createApp({
     data() {
     return {
     header : null,
     courses: "",
     filteredCourses: [],
     teachers:[],
     priceRange:[],
     filteredByPrice: []
     }
     },

     created() {          
     axios.get(`http://localhost:8080/api/courses`)
     .then(datos => {
          this.courses = datos.data
          this.filteredCourses = datos.data
          this.teachers = this.courses[0].teacher
     })

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