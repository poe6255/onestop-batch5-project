 
 
 const Delivery = () => {
   return (
     <div className='w-full bg-white py-16 px-4'>
        <h3 className='text-orange-500 font-bold text-2xl text-center'>Quick Delivery App</h3>
        <div className='w-[1520px] mx-auto grid md:grid-cols-2'>
            <img className='w-[550px] mx-auto my-4' 
            src="https://res.cloudinary.com/ehizeex-shop/image/upload/v1672672076/NetflixApp/FC_two_phones.6ec9a842f905769677f9_m91off.webp"
             alt="" />

             <div className='flex flex-col justify-center'>
                <p className='text-[#00df9a] font-bold'>Get The App</p>
                <h1 className='md:text-4xl sm:text-3xl text-2xl font-bold py-2'>Limitless Convenience 0n-demand </h1>
                <p className='text-justify p-3 pe-20'>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur non animi nam tenetur quo in temporibus iste, rerum maiores quasi fuga veritatis at. Dignissimos molestias odit illo provident perferendis nihil.
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse culpa magni maiores maxime cum necessitatibus id nobis adipisci natus voluptas, provident ea molestias ullam enim facilis quasi blanditiis minima facere!
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore molestiae ratione fuga odio dolorum accusamus tempore maxime soluta pariatur et doloribus natus culpa, nostrum, illum facere. Repellendus explicabo nostrum aperiam?
                </p>
                <button className='bg-black text-[#00df9a] w-[200px] rounded-md font-medium my-6 mx-auto md:mx-0 py-3'>Get Started</button>
             </div>
        </div>
        
     </div>
   )
 }
 
 export default Delivery