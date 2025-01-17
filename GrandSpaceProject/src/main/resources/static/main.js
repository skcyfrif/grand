// Define the custom header element
class MyHeader extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
<header>
            <div class="sg_top_header">
                <div class="gs_header_top_sec">
                    <div class="sg_top_left_box">
                        <ul class="gs_inline_items">
                            <li class="icon_list_item">
                                <a href="tel:+91 9583215595">
                                    <span class="gs_contact_number"><i class="fa fa-phone"
                                            aria-hidden="true"></i></span>
                                    <span class="gs_contac_num">+91 9583215595</span>
                                </a>
                            </li>
                            <li class="icon_list_item">
                                <a href="mailti:grandspace23@gmail.com">
                                    <span class="gs_contact_number"><svg xmlns="http://www.w3.org/2000/svg"
                                            version="1.1" xmlns:xlink="http://www.w3.org/1999/xlink" width="20"
                                            height="20" x="0" y="0" viewBox="0 0 60 60"
                                            style="enable-background:new 0 0 512 512" xml:space="preserve" class="">
                                            <g>
                                                <g fill="#000" fill-rule="nonzero">
                                                    <path
                                                        d="M31.238 33.99a22.112 22.112 0 0 1-6.604-1.042 9.99 9.99 0 0 1-6.636-7.563c-.753-3.626.62-7.46 3.764-10.52a17.43 17.43 0 0 1 1.052-.944 12.746 12.746 0 0 1 11.605-2.505 9.717 9.717 0 0 1 6.756 8.937 9.206 9.206 0 0 1-2.152 6.506 5.058 5.058 0 0 1-4.946 1.816A2.857 2.857 0 0 1 32.24 27.4a2.715 2.715 0 0 1-.264-2.11c.875-3.306 1.764-8.33 1.773-8.38a1 1 0 1 1 1.97.348c-.037.209-.913 5.157-1.809 8.543a.722.722 0 0 0 .045.569.89.89 0 0 0 .58.357 3.106 3.106 0 0 0 2.985-1.185 7.198 7.198 0 0 0 1.66-5.087 7.735 7.735 0 0 0-5.356-7.13 10.701 10.701 0 0 0-9.734 2.135c-.321.267-.634.546-.934.838-1.554 1.512-4.037 4.65-3.2 8.68a8.098 8.098 0 0 0 5.285 6.064c4.69 1.496 11.43 1.677 14.98-2.738a1 1 0 0 1 1.559 1.252c-2.626 3.266-6.622 4.434-10.542 4.434z"
                                                        fill="#494c5d" opacity="1" data-original="#000000" class="">
                                                    </path>
                                                    <path
                                                        d="M27.815 28.805a4.598 4.598 0 0 1-2.92-.981c-1.919-1.536-1.972-4.205-1.434-6.022.182-.605.425-1.19.727-1.745a8.198 8.198 0 0 1 3.435-3.565 4.729 4.729 0 0 1 5.563.896 7.484 7.484 0 0 1 1.594 2.746 1 1 0 0 1-1.884.67 5.579 5.579 0 0 0-1.156-2.035 2.707 2.707 0 0 0-3.234-.483 6.269 6.269 0 0 0-2.565 2.733 7.16 7.16 0 0 0-.563 1.356c-.389 1.314-.303 3.03.769 3.888 1.172.942 3.15.544 4.263-.416a11.152 11.152 0 0 0 2.113-2.572 1 1 0 1 1 1.7 1.055 13.128 13.128 0 0 1-2.503 3.027 6.144 6.144 0 0 1-3.905 1.448z"
                                                        fill="#494c5d" opacity="1" data-original="#000000" class="">
                                                    </path>
                                                    <path
                                                        d="M57 60H3a3.003 3.003 0 0 1-3-3V20a1 1 0 0 1 1.64-.769l24.536 20.392a6.005 6.005 0 0 0 7.65 0L58.36 19.23A1 1 0 0 1 60 20v37a3.003 3.003 0 0 1-3 3zM2 22.131V57c0 .552.448 1 1 1h54a1 1 0 0 0 1-1V22.131l-22.898 19.03a8.01 8.01 0 0 1-10.203.002z"
                                                        fill="#494c5d" opacity="1" data-original="#000000" class="">
                                                    </path>
                                                    <path
                                                        d="M1.001 21a1 1 0 0 1-.58-1.816l9-6.38a1 1 0 0 1 1.157 1.632l-9 6.38a.994.994 0 0 1-.577.184zM58.999 21a.994.994 0 0 1-.577-.184l-9-6.38a1 1 0 0 1 1.156-1.632l9 6.38A1 1 0 0 1 59 21zM39.24 7a.997.997 0 0 1-.578-.184l-4.78-3.39a6.01 6.01 0 0 0-7.703-.047l-4.84 3.437a1 1 0 1 1-1.157-1.632l4.78-3.39a7.963 7.963 0 0 1 10.137.046l4.72 3.344A1 1 0 0 1 39.239 7zM1.65 59.46a1 1 0 0 1-.64-1.77l22.82-18.96a1 1 0 1 1 1.278 1.539l-22.82 18.96a.997.997 0 0 1-.638.231zM58.349 59.46a.994.994 0 0 1-.638-.231l-22.82-18.96a1 1 0 1 1 1.278-1.538l22.82 18.96a1 1 0 0 1-.64 1.77z"
                                                        fill="#494c5d" opacity="1" data-original="#000000" class="">
                                                    </path>
                                                    <path
                                                        d="M50 28.48a1 1 0 0 1-1-1V7.008c-.003.02-.043-.008-.11-.008H11.11a.162.162 0 0 0-.12.043L11 27.48a1 1 0 0 1-2 0V7a2.06 2.06 0 0 1 2.11-2h37.78A2.06 2.06 0 0 1 51 7v20.48a1 1 0 0 1-1 1z"
                                                        fill="#494c5d" opacity="1" data-original="#000000" class="">
                                                    </path>
                                                </g>
                                            </g>
                                        </svg></span>
                                    <span class="gs_contac_num">grandspace23@gmail.com</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="social_icon_heading">
                    <h6>Follow Us:</h6>
                    <ul class="gs_social_med_link">
                        <li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                        <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                        <li>
                            <a href="#">
                                <i title="X" aria-hidden="true">
                                    <img src="assets/images/iconsvg/twiter.svg" alt="">
                                </i>
                            </a>
                        </li>
                        

                        <li><a href="#"><i class="fa fa-whatsapp" aria-hidden="true"></i></a></li>
                        <li><a href="#"><i class="fa fa-youtube-play" aria-hidden="true"></i></a></li>
                    </ul>
                </div>
            </div>
            <div class="gs_header_ses gs_spc_lr" id="header">
                 <nav class="navbar navbar-expand-lg navbar-darkv">
                        <div class="container-fluid">
                            <div class="logo_img_contener">
                                <a class="navbar-brand" href="index.html"><img src="assets/images/sg_logo.png" alt=""></a>
                            </div>
                            <!-- Toggle button for mobile offcanvas menu -->
                            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                                aria-controls="offcanvasNavbar">
                                <span class="navbar-toggler-icon"></span>
                            </button>

                            <!-- Desktop Menu (visible on larger screens) -->
                            <div class="collapse navbar-collapse d-none d-lg-flex justify-content-end sg_desktop_Hopemenu">
                                <ul class="navbar-nav sg_desktop_menu">
                                    <li class="nav-item"><a class="nav-link" href="index.html">Home</a></li>
                                    <li class="nav-item"><a class="nav-link" href="services.html">Services</a></li>
                                    <li class="nav-item"><a class="nav-link" href="about.html">About Us</a></li>
                                    <li class="nav-item"><a class="nav-link" href="project.html">Project</a></li>
                                    <li class="nav-item"><a class="nav-link" href="gallery.html">Gallery</a></li>
                                    <li class="nav-item"><a class="nav-link" href="contact.html">Contact Us</a></li>
                                </ul>

                                <div class="gs_button_two_botton">
                                    <button class="button gs_header_btn gs_regist_botton" onclick="window.location.href='Register.html';">
                                        <span>Register</span>
                                    </button>
                                    <button class="button gs_header_btn_login" onclick="window.location.href='gs-login.html';">
                                        <span>Sign in</span>
                                    </button>
                                </div>
                            </div>

                            <!-- Offcanvas Menu (visible on smaller screens) -->
                            <div class="offcanvas offcanvas-start sg_offcanvas_menu" tabindex="-1" id="offcanvasNavbar"
                                aria-labelledby="offcanvasNavbarLabel">
                                <div class="offcanvas-header">
                                    <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Menu</h5>
                                    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                                </div>
                                <div class="offcanvas-body">
                                    <ul class="navbar-nav">
                                        <li class="nav-item"><a class="nav-link" href="index.html">Home</a></li>
                                        <li class="nav-item"><a class="nav-link" href="services.html">Services</a></li>
                                        <li class="nav-item"><a class="nav-link" href="about.html">About Us</a></li>
                                        <li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
</nav>
            </div>
        </header>`;
    }
}

// Define the custom footer element
class MyFooter extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
 <footer class="gs_footer_sec">
        <div class="gs_footer_bg gs_spc_lr ">
            <!-- <div class="gs_footer_Body">  </div> -->
            <div class="footer_container">
                <div class="gs_footer_box1">
                    <div class="gs_footer_box1_top"><a href="index.html"><img src="assets/images/sg_logo.png"
                                alt=""></a></div>
                    <div class="gs_footer_box1_botton">
                        <p>Excellence in every detail, accuracy in every build.</p>
                        <form class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </div>
                </div>
                <div class="gs_footer_box2">
                    <div class="gs_footer_box2_headinga">
                        <h2>Useful Links</h2>
                    </div>
                    <div class="gs_footer_box2_bottom">
                        <ul>
                            <li class="gs_footer_list_iteam">
                                <a href="about.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">About</span>
                                </a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="services.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Services</span>
                                </a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="project.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Our Projects</span>
                                </a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="gallery.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Gallery</span>
                                </a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="faqs.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Faqs</span>
                                </a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="contact.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Contact Us</span>
                                </a>
                            </li>
                             <li class="gs_footer_list_iteam">
                                <a href="partner-registration-form.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Partner Registration</span>
                                </a>
                            </li>
                        </ul>

                    </div>
                </div>
                <div class="gs_footer_box3">
                    <div class="gs_footer_box3_headinga">
                        <h2>Our Services</h2>
                    </div>
                    <div class="gs_footer_box2_bottom">
                        <ul>
                            <li class="gs_footer_list_iteam">
                                <a href="land.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Construction</span>
                                </a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="interior.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Interior</span>
                                </a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="land.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Land</span>
                                </a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="project.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Upcoming Projects</span>
                                </a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="completed-projects.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Completed Projects</span>
                                </a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="privacy-policy.html">
                                    <span class="gs_footer_iteam_heading"><svg aria-hidden="true"
                                            class="e-font-icon-svg e-fas-tape" viewBox="0 0 640 512"
                                            xmlns="http://www.w3.org/2000/svg">
                                            <path
                                                d="M224 192c-35.3 0-64 28.7-64 64s28.7 64 64 64 64-28.7 64-64-28.7-64-64-64zm400 224H380.6c41.5-40.7 67.4-97.3 67.4-160 0-123.7-100.3-224-224-224S0 132.3 0 256s100.3 224 224 224h400c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400-64c-53 0-96-43-96-96s43-96 96-96 96 43 96 96-43 96-96 96z">
                                            </path>
                                        </svg></span>
                                    <span class="gs_footer_iteam_link">Privacy-Policy</span>
                                </a>
                            </li>
                        </ul>

                    </div>
                </div>
                <div class="gs_footer_box4">
                    <div class="gs_footer_box2_headinga">
                        <h2>Contact Us</h2>
                    </div>
                    <div class="gs_footer_box2_bottom">
                        <ul>
                            <li class="gs_footer_list_iteam">
                                <p> Kalinga Vihar, Bhubaneswar</p>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="tel:9241678848">+91 9583215595</a>
                            </li>
                            <li class="gs_footer_list_iteam">
                                <a href="mailto:bkhamari@gmail.com">grandspace23@gmail.com</a>
                            </li>
                        </ul>
                        <div class="gs_opean_time">
                            <div class="gs_opean_hear">
                                <h6>Open Hours:</h6>
                            </div>
                            <p>Mon – Sat: 8 am – 5 pm<br>
                                Sunday: CLOSED</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="copyrait_container">
            <p>© 2024 grandspace. All Rights Reserved. Designed By <a href="#">cyfrifpro</a></p>
        </div>
    </footer>`;
    }
}

// Register custom elements
customElements.define('my-header', MyHeader);
customElements.define('my-footer', MyFooter);