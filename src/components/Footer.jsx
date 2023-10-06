
import { FaWhatsappSquare,FaFacebookSquare,FaGithubSquare,FaTwitterSquare,FaInstagramSquare,FaTelegramPlane } from 'react-icons/fa'

const Footer = () => {
  return (
    <div className='max-w-[1520px] m-auto px-4 py-2 bg-[#24262b]'>
        <div className='py-16 px-4 grid lg:grid-cols-3 gap-8 text-gray-300'>
            <div>
                <h1 className='w-full text-3xl font-bold text-orange-500'>YumEats</h1>
                <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Eos officiis, eaque praesentium non iusto asperiores voluptatem vel commodi magni sed sequi illum reprehenderit. Quam libero sit nostrum, hic in commodi.</p>
                <div className='flex justify-between md:w-[75%] my-6'>
                    <FaFacebookSquare size={30} />
                    <FaInstagramSquare size={30}/>
                    <FaTwitterSquare size={30} />
                    <FaGithubSquare size={30} />
                    <FaWhatsappSquare size={30} />
                    <FaTelegramPlane size={30} />
                </div>
            </div>
            <div className='lg:col-span-2 flex justify-between mt-6'>
                <div>
                    <h5 className='font-medium text-[#9b9b9b]'>Location</h5>
                    <ul>
                        <li className='py-2 text-sm'>London</li>
                        <li className='py-2 text-sm'>USA</li>
                        <li className='py-2 text-sm'>India</li>
                        <li className='py-2 text-sm'>Canada</li>
                        <li className='py-2 text-sm'>Singapore</li>
                    </ul>
                </div>

                <div>
                    <h5 className='font-medium text-[#9b9b9b]'>Location</h5>
                    <ul>
                        <li className='py-2 text-sm'>London</li>
                        <li className='py-2 text-sm'>USA</li>
                        <li className='py-2 text-sm'>India</li>
                        <li className='py-2 text-sm'>Canada</li>
                        <li className='py-2 text-sm'>Singapore</li>
                    </ul>
                </div>

                <div>
                    <h5 className='font-medium text-[#9b9b9b]'>Location</h5>
                    <ul>
                        <li className='py-2 text-sm'>London</li>
                        <li className='py-2 text-sm'>USA</li>
                        <li className='py-2 text-sm'>India</li>
                        <li className='py-2 text-sm'>Canada</li>
                        <li className='py-2 text-sm'>Singapore</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
  )
}

export default Footer