import React from 'react'
import {IconContext} from 'react-icons'
import {MdCopyright} from 'react-icons/md'
import {FaLinkedin} from 'react-icons/fa'
import {FaGithub} from 'react-icons/fa'
import {SiGmail} from 'react-icons/si'

const Footer = () => {

    const currentYear = new Date().getFullYear();
    return (
        <IconContext.Provider value={{color:'white', size:'1rem'}}>
            <div className="footer">
                <div className="copyright">
                    <span className="copyright-info">
                        <span>
                            <MdCopyright />   
                            {currentYear}
                        </span>
                        <span>Huiyi Skårner</span>
                    </span>
                    <span className="social-icons">
                        <a href="https://www.linkedin.com/in/sk%C3%A5rner-huiyi-b2345b1b9/" target="_blank"><FaLinkedin /></a>
                        <a href="https://github.com/hui1010?tab=repositories" target="_blank"><FaGithub/></a>
                        <a href="mailto:huiyi.skarner@gmail.com"><SiGmail/></a>
                    </span>
                </div>
            </div>
        </IconContext.Provider>
    )
}

export default Footer
